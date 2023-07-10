// ------------------列出資料------------------
$(document).ready(function () {
    const tableBody = document.getElementById('tableBody');
    axios.get("/product/get/all")
        .then( function (res) {
            console.log(res.data);
            res.data.forEach(element => {

                const base64Img = `data:image/*;base64,${element.pdImg}`;

                const row = `
                            <tr>
                                <td class="pdId" style="color: #a67c52;">${element.pdId}</td>
                                <td class="pdId" style="color: #a67c52;">
                                    <img class="card-img-top" src="${base64Img}" alt="image"></td>
                                <td class="pdType" style="color: #a67c52;">${element.pdType}</td>
                                <td class="pdName" style="color: #a67c52;">${element.pdName}</td>
                                <td class="pdName" style="color: #a67c52;">${element.pdStatus === "0" ? "下架" : "上架"}</td>
                                <td class="pdPrice" style="color: #a67c52;">\$ ${element.pdPrice}</td>
                                <td class="pdDate" style="color: #a67c52;">${element.pdDate}</td>
                                <td>
                                    <button class="btn btn-primary btn_update" type="button"
                                        style="background: #f1ecd1;border-style: none;color: #a67c52;">修改</button>
                                </td>
                            </tr>
                            `
                tableBody.innerHTML += row;
            })
            $("#myTable").DataTable({   //初始化DataTable
                "language": {
                    "lengthMenu": "顯示 _MENU_ 條",
                    "zeroRecords": "未找到任何資料",
                    "info": "顯示頁數 _PAGE_ / _PAGES_",
                    "infoEmpty": "沒有任何資訊",
                    "infoFiltered": "(從 _MAX_ 條資料中過濾)",
                    "search": "搜尋:",
                    "paginate": {
                        "first":      "第一頁",
                        "last":       "最後一頁",
                        "next":       "»",
                        "previous":   "«"
                    }
                }
            });
        })
        .catch(err => console.log(err));

})

// ------------------修改資料------------------
$(document).on('click', "button.btn_update", function () {
    let pdId = $(this).closest("tr").find("td.pdId").text();
    console.log(pdId);

    sessionStorage.setItem("pdId", pdId);
    location.href = "/news_product/PdUpdate.html";

})