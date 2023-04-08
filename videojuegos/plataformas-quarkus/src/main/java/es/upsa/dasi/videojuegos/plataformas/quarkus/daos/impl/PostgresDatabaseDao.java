package es.upsa.dasi.videojuegos.plataformas.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.plataformas.quarkus.daos.DatabaseDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PostgresDatabaseDao implements DatabaseDao {
    @Inject
    DataSource dataSource;

    @Override
    public Plataformas selectPlataformasPelicula(String id) throws VideojuegoException {

        List<Plataformas> plataformas = new ArrayList<>();

        final String SQL = """
                           SELECT p.id, p.id_desarrolladora, p.nombre, p.fecha_lanzamiento, p.foto
                             FROM plataformas p
                            WHERE p.id_videojuego = ? 
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SQL )
        )
        {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next() )
                {
                    plataformas.add( Plataformas.builder()
                            .withId( resultSet.getString(1) )
                            .withId_desarrolladora( resultSet.getString(2) )
                            .withNombre(resultSet.getString(3))
                            .withFecha_lanzamiento(resultSet.getDate(4))
                            .withFoto(resultSet.getString(5))
                            .build()
                    );
                }
            }
        } catch (SQLException sqlException)
        {
            throw new VideojuegoException((sqlException));
        }
        Mappers mappers = new Mappers();

        return mappers.toPlataforma(id, plataformas);
    }
}
