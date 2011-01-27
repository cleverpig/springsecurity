package com.bjinfotech.demo.service;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-9
 * Time: 上午7:19
 * To change this template use File | Settings | File Templates.
 */
public class MyUserDetailsService implements UserDetailsService{
  private DataSource dataSource;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String sql = "SELECT u.username,u.password " +
                  "FROM users u " +
                  "WHERE u.username= :username";
    MapSqlParameterSource source = new MapSqlParameterSource();
    source.addValue("username", username);

    SimpleJdbcTemplate template = new SimpleJdbcTemplate(getDataSource());
    User user = template.queryForObject(sql, new UserMapper(), source);
    return user;
  }
  private List<GrantedAuthority> loadAuthoritiesByUsername(String username) {
    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
    String sql = "SELECT au.authority " +
                  "FROM authorities au " +
                  "WHERE au.username= :username";
    MapSqlParameterSource source = new MapSqlParameterSource();
    source.addValue("username", username);

    SimpleJdbcTemplate template = new SimpleJdbcTemplate(getDataSource());
    List<Map<String,Object>> mapList= template.queryForList(sql,source);
    for(Map<String,Object> map:mapList){
      String columnValue= (String) map.get("authority");
      authList.add(new GrantedAuthorityImpl(columnValue));
    }
    return authList;
  }

  private class UserMapper implements ParameterizedRowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new User(rs.getString("username"),rs.getString("password"),
          true, true, true, true,
          loadAuthoritiesByUsername(rs.getString("username"))
         );
    }

  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
