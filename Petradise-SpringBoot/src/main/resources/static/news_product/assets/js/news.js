// ------------------列出資料------------------
$(document).ready(function () {
    const tableBody = document.getElementById('tableBody');
    axios.get("/news/get/all")
        .then( function (res) {
            console.log(res.data);
            res.data.forEach(element => {
                const row = `
                    <tr>
                        <td class="newsId" style="color: #a67c52">${element.newsId}</td>
                        <td class="newsTitle" style="color: #a67c52">${element.newsTitle}</td>
<!--                        <td class="newsContent" style="color: #a67c52; display: none">${element.newsContent}</td>-->
                        <td class="newsDate" style="color: #a67c52">${element.newsDate}</td>
                        <td>
                            <button id="btn_update" class="btn btn-primary" type="button" style="background: #f1ecd1;border-style: none;color: #a67c52">修改</button>
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
$(document).on('click', "button#btn_update", function () {
    // const data = {};
    let newsId = $(this).closest("tr").find("td.newsId").text();
    // console.log(newsId);
    // data.newsTitle = $(this).closest("tr").find("td.newsTitle").text();
    // data.newsDate = $(this).closest("tr").find("td.newsDate").text();
    // data.newsContent = $(this).closest("tr").find("td.newsContent").text();

    sessionStorage.setItem("newsId", JSON.stringify(newsId));
    location.href = "/news_product/NewsUpdate.html";

})