// ------------------拿session資料------------------
$(document).ready(function () {
    const newsId = JSON.parse(sessionStorage.getItem("newsId"));
    // console.log("/news/get/" + newsID)

    axios.get("/news/get/" + newsId)
        .then(function (res) {
            // console.log(res.data);
            // console.log(res.data.newsTitle);
            $("#newsTitle").val(res.data.newsTitle);
            $("#newsDate").val(res.data.newsDate);
            $("#newsContent").val(res.data.newsContent);
        })
        .catch(err => console.log(err));

})

// ------------------修改資料------------------
const newsId = JSON.parse(sessionStorage.getItem("newsId"));
console.log(newsId)

$("#submit").on('click', function (e) {
    e.preventDefault();

    let newsTitle = $("#newsTitle").val().trim();
    let newsDate = $("#newsDate").val().trim();
    let newsContent = $("#newsContent").val().trim();

    if(newsTitle.length == 0 || newsDate.length == 0 || newsContent.length == 0) {
        Swal.fire({
            icon: 'error',
            title: '齁...',
            text: '欄位不可以空著唷!',
        });
    } else {

        const data = {};
        data.newsId = newsId;
        data.adminId = $("#adminId").val();
        data.newsTitle = $("#newsTitle").val();
        data.newsDate = $("#newsDate").val();
        data.newsContent = $("#newsContent").val();

        axios
            .put("/news/update/" + newsId, data)  // 設定url, object
            .then((res) => {
                console.log(res.data);  // 獲得回傳資料
            })
            .catch((err) => {
                console.error(err);
            });

        location.href = "/news_product/News.html";
    }
});