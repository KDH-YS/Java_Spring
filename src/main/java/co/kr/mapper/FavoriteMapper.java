package co.kr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



import co.kr.entity.Favorite;
import co.kr.entity.User;

@Mapper
public interface FavoriteMapper {
    void insertFavorite(Favorite favorite);  // XML 매퍼에서 정의된 쿼리 호출
    List<Favorite> getFavoritesByUserId(Integer userId);  // XML 매퍼에서 정의된 쿼리 호출
    int checkFavoriteExists(Favorite favorite); // 중복 확인 메서드 추가
    
	User findUser(String username);
	void signup(User user);
	void UserRoleMapping();
	void newrole();
	List<User> findAll();
}
