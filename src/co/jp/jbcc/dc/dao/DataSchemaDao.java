package co.jp.jbcc.dc.dao;

import java.util.List;

import co.jp.jbcc.dc.common.Constants;
import co.jp.jbcc.dc.dto.DataSchemaDto;

public class DataSchemaDao extends DaoBase {

	public DataSchemaDto bind2Dto(String query) {
		return super.bind2Dto( Constants.DB_DATA_CREATOR, query, DataSchemaDto.class );
	}

	public List<DataSchemaDto> bind2Dtos(String query) {
		return super.bind2Dtos( Constants.DB_DATA_CREATOR, query, DataSchemaDto.class );
	}

}
