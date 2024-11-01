package co.kr.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.kr.entity.Favorite;
import co.kr.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody Favorite favorite) {
        if (favorite == null || favorite.getUserId() <= 0) {
            return ResponseEntity.badRequest().body("잘못된 요청입니다. 유효한 즐겨찾기 정보를 제공하세요.");
        }
        
        try {
            favoriteService.addFavorite(favorite);
            return ResponseEntity.ok(Collections.singletonMap("message", "즐겨찾기가 성공적으로 추가되었습니다."));
        } catch (Exception e) {
            log.info("Error adding favorite: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "즐겨찾기 추가 중 오류가 발생했습니다."));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Favorite>> getFavorites(@RequestParam("userId") int userId) {
        if (userId <= 0) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            List<Favorite> favorites = favoriteService.getFavorites(userId);
            return ResponseEntity.ok(favorites);
        } catch (Exception e) {
            logger.error("Error retrieving favorites for user {}: ", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}
