package org.jleaf.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.treeleaf.db.ConnectionContext;
import org.treeleaf.db.DBConnectionFactory;
import org.treeleaf.db.MySqlDBModelOperator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 程序启动入口
 *
 * @author leaf
 * @date 2016-10-13 15:28
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * 数据源
     *
     * @return
     */
    @Bean
    public DataSource druidDataSource(
            @Value("${spring.datasource.driverClassName}") String driver,
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setFilters("stat, wall");
        ConnectionContext.setDbConnectionFactory(new SpringDBConnectionFactory(dataSource));

        return dataSource;
    }

    public static class SpringDBConnectionFactory extends DBConnectionFactory {

        private DataSource dataSource;

        public SpringDBConnectionFactory(DataSource dataSource) {
            this.dataSource = dataSource;
            ConnectionContext.setDbConnectionFactory(this);
        }

        public Connection getConnection() {
            return DataSourceUtils.getConnection(this.dataSource);
        }
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        //reg.addInitParameter("allow", "127.0.0.1");
        //reg.addInitParameter("deny","");
        reg.addInitParameter("loginUsername", "admin");
        reg.addInitParameter("loginPassword", "admin");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public MySqlDBModelOperator mySqlDBModelOperator() {
        return new MySqlDBModelOperator();
    }
}
