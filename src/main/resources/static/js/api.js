async function search() {
    const query = document.getElementById("searchQuery").value;
    try {
        const response = await fetch(`/api/search?query=${encodeURIComponent(query)}`);
        if (!response.ok) throw new Error('검색에 실패했습니다.');
        const results = await response.json();
        displaySearchResults(results);
    } catch (error) {
        console.error(error);
        alert('검색 중 오류가 발생했습니다.');
    }
}

function displaySearchResults(results) {
    const searchResults = document.getElementById("searchResults");
    searchResults.innerHTML = '';
    
    results.forEach(item => {
        const itemDiv = document.createElement("div");
        const cleanTitle = item.title.replace(/<[^>]*>/g, ''); // HTML 태그 제거
        itemDiv.innerHTML = `
            <p>${escapeHTML(cleanTitle)}</p>
			<img src="${escapeHTML(item.image)}" alt="${escapeHTML(cleanTitle)}" style="max-width: 100px; max-height: 100px;">
            <a href="${escapeHTML(item.link)}" target="_blank">링크</a>
            <button onclick="addFavorite('${item.productId}', '${escapeHTML(cleanTitle)}', '${escapeHTML(item.link)}', '${escapeHTML(item.category1)}')">즐겨찾기 추가</button>
        `;
        searchResults.appendChild(itemDiv);
    });
}

async function addFavorite(productId, title, link, category1) {
    const favorite = {
        userId: 1,      // 예시 사용자 ID, 동적으로 변경해야 함
        productId: productId,
        productType: 'news',
        title: title,
        link: link,
        category1: category1 // category1 추가
    };

    try {
        const response = await fetch('/api/favorites/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(favorite)
        });
        if (!response.ok) throw new Error('즐겨찾기 추가에 실패했습니다.');

        loadFavorites();
    } catch (error) {
        console.error(error);
        alert('즐겨찾기 추가 중 오류가 발생했습니다.');
    }
}

async function loadFavorites() {
    try {
        const response = await fetch('/api/favorites/list?userId=1');
        if (!response.ok) throw new Error('즐겨찾기 목록을 불러오는 데 실패했습니다.');
        const favorites = await response.json();

        const favoriteList = document.getElementById("favoriteList");
        favoriteList.innerHTML = '';

        favorites.forEach(fav => {
            const favDiv = document.createElement("div");
            const cleanTitle = fav.title.replace(/<[^>]*>/g, ''); // HTML 태그 제거
            favDiv.innerHTML = `
                <p>${escapeHTML(cleanTitle)} - <a href="${escapeHTML(fav.link)}" target="_blank">링크</a></p>
            `;
            favoriteList.appendChild(favDiv);
        });
    } catch (error) {
        console.error(error);
        alert('즐겨찾기 목록을 불러오는 중 오류가 발생했습니다.');
    }
}

function escapeHTML(str) {
    return str.replace(/[&<>"']/g, tag => 
        ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[tag] || tag));
}

document.addEventListener("DOMContentLoaded", loadFavorites);
