package dao;

import java.util.List;

import mejoras.bolsa.web.HistoricoOperacionMejora;

public interface CarpetaDao extends Dao< CarpetaDao, List> {

	HistoricoOperacionMejora getData(); 

}
