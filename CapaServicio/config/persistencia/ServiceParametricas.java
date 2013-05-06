package persistencia;

import java.util.List;

import javax.ejb.Local;

import entity.BaseEntity;

@Local
public interface ServiceParametricas {

	<DETALLE,MAESTRO extends BaseEntity> List<DETALLE> listDetalle(DETALLE example, MAESTRO maestro, String property);
}
