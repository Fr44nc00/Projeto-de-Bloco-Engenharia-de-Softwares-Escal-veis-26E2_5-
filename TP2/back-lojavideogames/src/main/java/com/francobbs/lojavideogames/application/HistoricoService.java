package com.francobbs.lojavideogames.application;

import com.francobbs.lojavideogames.domain.Usuario;
import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.domain.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> listarHistoricoUsuario(Long usuarioId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        return (List<Usuario>) reader.createQuery()
                .forRevisionsOfEntity(Usuario.class, true, true)
                .add(AuditEntity.id().eq(usuarioId))
                .getResultList();
    }

    public List<Jogo> listarHistoricoJogo(Long jogoId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        return (List<Jogo>) reader.createQuery()
                .forRevisionsOfEntity(Jogo.class, true, true)
                .add(AuditEntity.id().eq(jogoId))
                .getResultList();
    }

    public List<Compra> listarHistoricoCompra(Long compraId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        return (List<Compra>) reader.createQuery()
                .forRevisionsOfEntity(Compra.class, true, true)
                .add(AuditEntity.id().eq(compraId))
                .getResultList();
    }
}
