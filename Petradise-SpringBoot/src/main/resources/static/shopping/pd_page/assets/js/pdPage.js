const data = {};
// ------------------拿session資料------------------
$(document).ready(function () {
    const pdId = JSON.parse(sessionStorage.getItem("pdId"));
    // console.log("/product/get/" + pdId)

    // 購物車項目初始化
    let shoppingItem = sessionStorage.getItem("shoppingItem");
    if (shoppingItem) {
        shoppingItem = JSON.parse(shoppingItem);
    } else {
        shoppingItem = {};
    }

    axios.get("/product/get/" + pdId)
        .then(function (res) {
            console.log(res.data);

            // 列出商品圖
            const base64Img = `data:image/*;base64,${res.data.pdImg}`;
            $("#pdName").text(res.data.pdName);
            $("#pdPetType").text(res.data.pdPetType);
            $("#pdType").text(res.data.pdType);
            $("#pdPrice").text("$ " + res.data.pdPrice);
            $("#pdInfo").text(res.data.pdInfo);
            $("#preview").html(`<img src="${base64Img}" alt="image" style="width: 45%">`);
            data.pdImg = base64Img;


            // 加購物車監聽
            $(".cart-btn").click(function (e) {
                e.preventDefault();
                const productId = pdId;
                const productName = res.data.pdName;
                const productType = res.data.pdType;
                const productPetType = res.data.pdPetType;
                const productPrice = res.data.pdPrice;
                const productImage = data.pdImg.split(",")[1];
                const productQuantity = 1;

                addToCart(productId, productName, productType, productPetType, productPrice, productImage, productQuantity);
            });

            //  加入購物車Function + 存session
            function addToCart(productId, productName, productType, productPetType, productPrice, productImage, productQuantity) {
                if (shoppingItem.hasOwnProperty(productId)) {
                    shoppingItem[productId].quantity += productQuantity;
                } else {
                    shoppingItem[productId] = {
                        id: productId,
                        name: productName,
                        type: productType,
                        petType: productPetType,
                        price: productPrice,
                        quantity: productQuantity,
                        image: productImage
                    };
                }
                updateCartIcon();
                console.log(shoppingItem);
                sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));
            };

            // 更新購物車商品數
            function updateCartIcon() {
                // const totalItems = Object.keys(shoppingItem).length;
                // $(".shopping-cart-total").text(`( ${totalItems} )`);
                let totalItems = 0;
                for (const productId in shoppingItem) {
                    totalItems += shoppingItem[productId].quantity;
                }
                $(".shopping-cart-total").text(`(${totalItems})`);
            }

            // 初始化購物車圖示數量
            updateCartIcon();
        })
        .catch(err => console.log(err));

});

// ------------------結帳------------------
$(".cart").on('click', function () {
    location.href = "Cart.html";
});