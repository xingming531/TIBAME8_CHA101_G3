// ------------------購物車項目初始化------------------
let shoppingItem = sessionStorage.getItem("shoppingItem");
if (shoppingItem) {
    shoppingItem = JSON.parse(shoppingItem);
} else {
    shoppingItem = {};
}

// 更新購物車商品數
function updateCartIcon() {
    let totalItems = 0;
    for (const productId in shoppingItem) {
        totalItems += shoppingItem[productId].quantity;
    }
    $(".shopping-cart-total").text(`(${totalItems})`);
}

// 初始化購物車圖示數量
updateCartIcon();


// ------------------前往購物車------------------
$(".cart").on('click', function () {
    location.href = "/shopping/pd_page/Cart.html"
});
