$(document).ready(function () {
    guardIsSignedIn()

    // ------------------拿session資料----------------------------------------------------
    // 初始化 shoppingItem ----------------------------------------------------
    // cartIcon.js宣告過了
    // console.log(shoppingItem);

    // 更新購物車商品數
    // cartIcon.js宣告過了

    // 初始化購物車圖示數量
    // cartIcon.js宣告過了

    // 初始化 checkoutItem ----------------------------------------------------
    const checkoutItem = {};
    sessionStorage.setItem("checkoutItem", JSON.stringify(checkoutItem));

    // ------------------渲染購物車項目----------------------------------------------------
    let totalItems = 0;
    const cartBody = document.getElementById('cartBody');

    for (const productId in shoppingItem) {
        const item = shoppingItem[productId];
        const base64Img = `data:image/*;base64,${item.image}`;
        const id = item.id;
        const name = item.name;
        const quantity = item.quantity;
        const pdType = item.type;
        const pdPetType = item.petType;
        const price = item.price * item.quantity;

        const row = `
        <div class="cart-items" id="${id}">
                <div class="check">
                    <input type="checkbox">
                </div>
                <div id="preview" class="image-box">
                    <img src="${base64Img}" style="height: 120px">
                </div>
    
                <div class="item-about">
                    <span id="id" style="display: none">${id}</span>
                    <span id="pdPetType" class="type">${pdPetType} \/ </span><span id="pdType" class="type">${pdType}</span>
                    <h1 id="pdName" class="title">${name}</h1>
                </div>
            
            <div class="item-counter">
                <div class="item-btn item-decrease">-</div>
                <div class="item-count">${quantity}</div>
                <div class="item-btn item-increase">+</div>

            </div>

            <div class="prices">
                <div id="pdPrice" class="amount">\$ ${price}</div>
                <div class="remove deleteOne"><u>移除</u></div>
            </div>
        </div>
        `;
        cartBody.innerHTML += row;

        // ------------------更新購物車數----------------------------------------------------
        totalItems += item.quantity;
        $(".shopping-cart-total").text(`(${totalItems})`);

    }


    // ------------------點擊 "+" 增加數量----------------------------------------------------
    $(document).on('click', '.item-btn.item-increase', function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        const item = shoppingItem[productId];

        item.quantity++; // 遞增數量----------------------------------------------------
        updateQuantity(productId, item.quantity); // 更新顯示的數量
        updateCheckoutItem();
    });

    // ------------------點擊 "-" 減少數量----------------------------------------------------
    $(document).on('click', '.item-btn.item-decrease', function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        const item = shoppingItem[productId];

        item.quantity--; // 遞減數量---------------------------------------------------------
        if (item.quantity < 1) {    // 確保數量不小於 1---------------------------------------
            Swal.fire({
                title: "確定要移除這個品項嗎？",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#f1ecd1",
                cancelButtonColor: "#f8c544",
                confirmButtonText: "是，請移除",
                cancelButtonText: "否，請保留",
            }).then((result) => {
                if (result.isConfirmed) {

                    // 從session的 shoppingItem checkoutItem清掉----------------------------------
                    const shoppingItem = JSON.parse(sessionStorage.getItem("shoppingItem"));
                    delete shoppingItem[productId];
                    sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));

                    // 取消勾選被移除的 checkbox
                    const checkbox = $(".cart-items").closest(`#${productId}`).find("input[type='checkbox']");
                    if (checkbox.prop("checked")) {
                        // alert(checkbox);
                        checkbox.prop("checked", false);
                        const checkoutItem = JSON.parse(sessionStorage.getItem("checkoutItem"));
                        delete checkoutItem[productId];
                        sessionStorage.setItem("checkoutItem", JSON.stringify(checkoutItem));
                    }

                    $(this).closest(".cart-items").fadeOut(1000, function () {
                        $(this).remove();
                    })

                    // 更新購物車品項----------------------------------------------------
                    updateCheckoutItem();
                    totalItems = updateTotalItems(shoppingItem);
                    $(".shopping-cart-total").text(`(${totalItems})`);
                }

            })
            item.quantity = 1;
        }
        updateQuantity(productId, item.quantity); // 更新顯示的數量---------------------------
        updateCheckoutItem();

    });

    // ------------------更新數量----------------------------------------------------
    function updateQuantity(productId, quantity) {
        const quantityElement = $(`#${productId} .item-count`);
        quantityElement.text(quantity);

        // 更新 shoppingItem 物件中的數量----------------------------------------------------
        shoppingItem[productId].quantity = quantity;

        // 將更新後的 shoppingItem 物件存回 session------------------------------------------
        sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));

        // 更新總數量-----------------------------------------------------------------------
        totalItems = updateTotalItems(shoppingItem);
        $(".shopping-cart-total").text(`(${totalItems})`);

        // 更新價格------------------------------------------------------------------------
        const priceElement = $(`#${productId} #pdPrice`);
        const item = shoppingItem[productId];
        const price = item.price * quantity;
        priceElement.text(`\$ ${price}`);

    }

    function updateTotalItems(shoppingItem) {
        let total = 0;
        for (const productId in shoppingItem) {
            total += shoppingItem[productId].quantity;
        }
        return total;
    }


    // ------------------移除項目----------------------------------------------------
    // 移除全部----------------------------------------------------
    $(document).on("click", ".deleteAll", function () {
        Swal.fire({
            title: "確定要清空購物車品項嗎？",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#f1ecd1",
            cancelButtonColor: "#f8c544",
            confirmButtonText: "是，請清空",
            cancelButtonText: "否，請保留",
        }).then((result) => {
            if (result.isConfirmed) {
                $(".cart-items").fadeOut(1000, function () {
                    $(this).remove();
                });
                // 清空session的 shoppingItem 和 checkoutItem
                sessionStorage.removeItem("shoppingItem");
                sessionStorage.removeItem("checkoutItem");

                // 更新購物車品項----------------------------------------------------
                updateCheckoutItem();
                $(".shopping-cart-total").text(`(0)`);
            }
        });
    });

    // 移除單項----------------------------------------------------
    $(document).on("click", ".deleteOne", function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        Swal.fire({
            title: "確定要移除這個品項嗎？",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#f1ecd1",
            cancelButtonColor: "#f8c544",
            confirmButtonText: "是，請移除",
            cancelButtonText: "否，請保留",
        }).then((result) => {
            if (result.isConfirmed) {

                // 從session的 shoppingItem checkoutItem清掉----------------------------------
                let shoppingItem = JSON.parse(sessionStorage.getItem("shoppingItem"));
                delete shoppingItem[productId];
                sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));

                // 取消勾選被移除的 checkbox
                let checkbox = $(".cart-items").closest(`#${productId}`).find("input[type='checkbox']");
                if (checkbox.prop("checked")) {
                    // alert(checkbox);
                    checkbox.prop("checked", false);
                    let checkoutItem = JSON.parse(sessionStorage.getItem("checkoutItem"));
                    delete checkoutItem[productId];
                    sessionStorage.setItem("checkoutItem", JSON.stringify(checkoutItem));
                }

                $(this).closest(".cart-items").fadeOut(1000, function () {
                    $(this).remove();
                })

                // 更新購物車品項----------------------------------------------------
                updateCheckoutItem();
                totalItems = updateTotalItems(shoppingItem);
                $(".shopping-cart-total").text(`(${totalItems})`);
            }
        });
    });


    // ------------------存待付款項目到session----------------------------------------------------
    // 點擊 checkbox----------------------------------------------------
    $(document).on('click', '.check input[type="checkbox"]', function () {
        const productId = $(this).closest('.cart-items').find('#id').text();
        const isChecked = $(this).prop('checked');

        if (isChecked) {
            // 勾選時將項目存入 checkoutItem------------------------------------------------------
            checkoutItem[productId] = shoppingItem[productId];
        } else {
            // 取消勾選時從 checkoutItem 移除項目--------------------------------------------------
            delete checkoutItem[productId];
        }
        updateCheckoutItem();
    });

    // 更新 checkoutItem 物件--------------------------------------------------
    function updateCheckoutItem() {
        sessionStorage.setItem("checkoutItem", JSON.stringify(checkoutItem));

        const itemsElement = $(".items");
        const totalAmountElement = $(".total-amount-span");

        let itemsCount = 0;
        let totalAmount = 0;

        for (const productId in checkoutItem) {
            const item = checkoutItem[productId];
            itemsCount += item.quantity;
            totalAmount += item.price * item.quantity;
        }

        itemsElement.text(`${itemsCount} 品項`);
        totalAmountElement.text(`$ ${totalAmount}`);
    }


    // ------------------繼續選購----------------------------------------------------
    $(".button-back").on("click", function () {
        location.href = "/front-product-list/front-product-list.html";
    })

    // ------------------前往結帳----------------------------------------------------
    $(".button-pay").on("click", function () {
        let checkExist = JSON.parse(sessionStorage.getItem("checkoutItem"));
        // console.log(checkExist);

        if (!checkExist || $.isEmptyObject(checkExist)) {
            Swal.fire({
                icon: 'warning',
                title: '喔喔...',
                text: '請先勾選商品再結帳喔~',
            });
            return;
        }

        // 從 checkbox 中取得被勾選的項目的產品 ID----------------------------------------------------
        const checkedItems = $(".cart-items input[type='checkbox']:checked");
        checkedItems.closest(".cart-items").fadeOut(1000, function () {
            $(this).remove();
        })
        const productIds = checkedItems.closest('.cart-items').map(function () {
            return $(this).attr('id');
        }).get();

        // 從 sessionStorage 的 shoppingItem 和 checkoutItem 移除被勾選的項目
        const shoppingItem = JSON.parse(sessionStorage.getItem("shoppingItem"));
        const checkoutItem = JSON.parse(sessionStorage.getItem("checkoutItem"));

        productIds.forEach(function (productId) {
            delete shoppingItem[productId];
            delete checkoutItem[productId];
        });

        sessionStorage.setItem("shoppingItem", JSON.stringify(shoppingItem));
        sessionStorage.setItem("checkoutItem", JSON.stringify(checkoutItem));


        // 更新購物車品項
        updateCheckoutItem();
        totalItems = updateTotalItems(shoppingItem);
        $(".shopping-cart-total").text(`(${totalItems})`);

        // 前往結帳頁面
        location.href = "/shopping/payment/payment.html";
    })

});