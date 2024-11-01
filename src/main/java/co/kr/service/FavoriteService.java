package co.kr.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import co.kr.entity.Favorite;
import co.kr.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteMapper favoriteMapper;


    public void addFavorite(Favorite favorite) {
        // 즐겨찾기 존재 여부 확인
        int count = favoriteMapper.checkFavoriteExists(favorite);
        if (count == 0) { // 존재하지 않을 경우에만 추가
            favoriteMapper.insertFavorite(favorite);
        }
    }


    public List<Favorite> getFavorites(int userId) {
        try {
            List<Favorite> favorites = favoriteMapper.getFavoritesByUserId(userId);
            return favorites != null ? favorites : new ArrayList<>();
        } catch (Exception e) {
            // 로그를 남기거나 예외 메시지를 출력할 수 있습니다.
            System.err.println("Error fetching favorites: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
