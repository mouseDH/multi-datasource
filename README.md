"# multi-datasource" 
注意：
   采用springboot时，会引入jdbcTemplate，对于使用mybatis的项目，jdbctemplate不会使用，此处可以考虑排出springboot的jdbc配置类。如果不去除，则需要制定一个数据源作为优先选择（可通过Primary注解标注)。
