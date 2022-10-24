package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Categoria;
import br.com.agls.pizzariafuturodev.repository.CategoriaRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria salvar(Categoria categoria) {
        boolean isNomeExistente = categoriaRepository.existsByNome(categoria.getNome());
        if (!isNomeExistente){
            return this.categoriaRepository.save(categoria);}
        else throw new EntityExistsException("Já existe uma categoria com o nome: " + categoria.getNome());
    }

    @Override
    public Categoria atualizar(Long id, Categoria categoria) {
        Optional<Categoria> categoria1 = categoriaRepository.findById(id);
        if (categoria1.isPresent()){
            BeanUtils.copyProperties(categoria, categoria1.get(), "id");
            return this.categoriaRepository.save(categoria1.get());
        }
        return null;
    }

    @Override
    public Categoria buscar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()){
            return categoria.get();
        }
        return null;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public String excluir(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()){
            categoriaRepository.delete(categoria.get());
            return "Excluido com sucesso!";
        }
        return "Id não encotrado!";
    }
}
