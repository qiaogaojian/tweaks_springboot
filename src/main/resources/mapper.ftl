package ${package};

import ${tableClass.fullClassName};
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ${tableClass.shortClassName}${mapperSuffix} extends Mapper<${tableClass.shortClassName}> {

}