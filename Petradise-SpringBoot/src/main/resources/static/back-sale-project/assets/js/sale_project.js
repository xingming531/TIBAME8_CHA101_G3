// 顯示全部活動
$(document).ready(function () {
    const sale_project_table_body = document.getElementById('sale_project_table_body');
    fetch("/sale/all", {
        method: 'GET'
    })
        .then(response => response.json())
        .then(onReceivedJSON)
        .catch(error => console.log('There was a problem with the fetch operation', error));
})

function onReceivedJSON(jsonData) {
    console.log(jsonData);
    for (let i of jsonData) {
        const row = `
            <tr>
                <td style="color: #a67c52;">${i.saleProId}</td>
                <td style="color: #a67c52;">${i.saleProName}</td>
                <td style="color: #a67c52;">${i.saleDiscount}</td>
                <td style="color: #a67c52;">${i.saleProStart}</td>
                <td style="color: #a67c52;">${i.saleProEnd}</td>


                <!-- 編輯活動 -->
                <td class="edit_sale_work">
                    <!-- Button trigger modal -->
                    <button type="button" class="edit_sale" data-bs-toggle="modal"
                        data-bs-target="#editBackdrop">
                        <img src="assets/img/edit_icon.png">
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="editBackdrop" data-bs-backdrop="static"
                        data-bs-keyboard="false" tabindex="-1"
                        aria-labelledby="staticBackdropLabel" aria-hidden="true">

                        <!-- Modal-dialog -->
                        <div
                            class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">
                                        編輯活動</h5>
                                    <button type="button" class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>


                                <!-- Body -->
                                <div class="modal-body edit_button_body">
                                    <article class="add_sale_project">
                                        <div class="mb-3">
                                            <label for="sale_project_name" class="form-label"
                                                style="color: #a67c52;">活動名稱：</label>
                                            <input type="text" class="form-control"
                                                id="sale_project_name">
                                        </div>
                                        <div class="mb-3">
                                            <label for="sale_product_type" class="form-label"
                                                style="color: #a67c52;">促銷商品編號：</label>
                                            <input type="text" class="form-control"
                                                id="sale_project_id">
                                        </div>
                                        <div class="mb-3">
                                            <label for="sale_project_discount" class="form-label"
                                                style="color: #a67c52;">活動折扣數：</label>
                                            <input type="text" class="form-control"
                                                id="sale_project_discount">
                                        </div>
                                        <div class="mb-3">
                                            <label for="sale_project_start" class="form-label"
                                                style="color: #a67c52;">活動起始日期：</label>
                                            <input type="date" class="form-control"
                                                id="sale_project_start">
                                        </div>

                                        <div class="mb-3">
                                            <label for="sale_project_end" class="form-label"
                                                style="color: #a67c52;">活動結束日期：</label>
                                            <input type="date" class="form-control"
                                                id="sale_project_end">
                                        </div>
                                    </article>


                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">取消</button>
                                    <button type="button"
                                        class="btn btn-primary">完成</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>

                <!-- 刪除活動 -->
                <td class="delete_sale_work">
                    <button type="button" class="delete_sale">
                        <img src="assets/img/delete_icon.png">
                    </button>
                </td>
            </tr>
        `
        sale_project_table_body.innerHTML += row;
    }
    // jsonData.forEach(element => {
    //     const row = `
    //         <tr>
    //             <td style="color: #a67c52;">${element.saleProId}</td>
    //             <td style="color: #a67c52;">${element.saleProName}</td>
    //             <td style="color: #a67c52;">${element.saleDiscount}</td>
    //             <td style="color: #a67c52;">${element.saleProStart}</td>
    //             <td style="color: #a67c52;">${element.saleProEnd}</td>


    //             <!-- 編輯活動 -->
    //             <td class="edit_sale_work">
    //                 <!-- Button trigger modal -->
    //                 <button type="button" class="edit_sale" data-bs-toggle="modal"
    //                     data-bs-target="#editBackdrop">
    //                     <img src="assets/img/edit_icon.png">
    //                 </button>

    //                 <!-- Modal -->
    //                 <div class="modal fade" id="editBackdrop" data-bs-backdrop="static"
    //                     data-bs-keyboard="false" tabindex="-1"
    //                     aria-labelledby="staticBackdropLabel" aria-hidden="true">

    //                     <!-- Modal-dialog -->
    //                     <div
    //                         class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
    //                         <div class="modal-content">
    //                             <div class="modal-header">
    //                                 <h5 class="modal-title" id="staticBackdropLabel">
    //                                     編輯活動</h5>
    //                                 <button type="button" class="btn-close"
    //                                     data-bs-dismiss="modal"
    //                                     aria-label="Close"></button>
    //                             </div>


    //                             <!-- Body -->
    //                             <div class="modal-body edit_button_body">
    //                                 <article class="add_sale_project">
    //                                     <div class="mb-3">
    //                                         <label for="sale_project_name" class="form-label"
    //                                             style="color: #a67c52;">活動名稱：</label>
    //                                         <input type="text" class="form-control"
    //                                             id="sale_project_name">
    //                                     </div>
    //                                     <div class="mb-3">
    //                                         <label for="sale_product_type" class="form-label"
    //                                             style="color: #a67c52;">活動商品種類：</label>
    //                                         <select name="sale_product_type" id="sale_product_type">
    //                                             <option value="" selected>請選擇商品種類</option>
    //                                             <option value="寶可夢">寶可夢</option>
    //                                             <option value="狗">狗</option>
    //                                             <option value="貓">貓</option>
    //                                             <option value="全部">全部</option>
    //                                         </select>
    //                                     </div>
    //                                     <div class="mb-3">
    //                                         <label for="sale_project_discount" class="form-label"
    //                                             style="color: #a67c52;">活動折扣數：</label>
    //                                         <input type="text" class="form-control"
    //                                             id="sale_project_discount">
    //                                     </div>
    //                                     <div class="mb-3">
    //                                         <label for="sale_project_start" class="form-label"
    //                                             style="color: #a67c52;">活動起始日期：</label>
    //                                         <input type="date" class="form-control"
    //                                             id="sale_project_start">
    //                                     </div>

    //                                     <div class="mb-3">
    //                                         <label for="sale_project_end" class="form-label"
    //                                             style="color: #a67c52;">活動結束日期：</label>
    //                                         <input type="date" class="form-control"
    //                                             id="sale_project_end">
    //                                     </div>
    //                                 </article>


    //                             </div>

    //                             <div class="modal-footer">
    //                                 <button type="button" class="btn btn-secondary"
    //                                     data-bs-dismiss="modal">取消</button>
    //                                 <button type="button"
    //                                     class="btn btn-primary">完成</button>
    //                             </div>
    //                         </div>
    //                     </div>
    //                 </div>
    //             </td>

    //             <!-- 刪除活動 -->
    //             <td class="delete_sale_work">
    //                 <button type="button" class="delete_sale">
    //                     <img src="assets/img/delete_icon.png">
    //                 </button>
    //             </td>
    //         </tr>
    //     `
    //     sale_project_table_body.innerHTML += row;
    // });
}


// 新增活動
$(document).ready(function () {
    $(document).on('click', '#add_confirm', function () {
        const insertData = {
            saleProName: $("#sale_project_name").val().trim(),
            pdId: +$("#sale_product_id").val(),
            saleDiscount: +($("#sale_project_discount").val()) / 10,
            saleProStart: $("#sale_project_start").val(),
            saleProEnd: $("#sale_project_end").val()
        }
        console.log(insertData);

        fetch('/sale/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // 必須要的設定
            },
            body: JSON.stringify(insertData) //轉JSON字串傳到後端
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(jsonData => {
                console.log(jsonData);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
        // location.reload(true);
    });
});