const data = {};
// ------------------選單------------------
const selector = {
    "寶可夢": ["寶可夢球", "樹果", "回復道具", "戰鬥道具", "其他"],
    "狗": ["飼料", "罐罐", "零食", "玩具", "保健", "其他"],
    "貓": ["飼料", "罐罐", "零食", "玩具", "保健", "其他"]
}
$("#pdPetType").on("change", function (e){
    $("#pdType").empty();
    const pdTypeArr = selector[e.target.value];
    pdTypeArr.forEach( function (type) {
        const option = $(`<option>`).val(type).text(type);
        $("#pdType").append(option);
    });
})


// ------------------預覽------------------
$("#pdImg").on("change", function () {         // 監聽input讀取圖片
    // 有選圖片的話
    if (this.files.length > 0) {
        let reader = new FileReader();                                          // 準備讀取檔案
        reader.addEventListener('load', function (e) {
            let base64Img = e.target.result.split(",")[1]; // 去除base64前墜
            $("#preview").html('<img src="' + reader.result + '" style="width: 100%">'); // 放圖片的src
            // console.log(base64Img);
            data.pdImg = base64Img;
            console.log(base64Img);
            console.log(data);
        });
        reader.readAsDataURL(this.files[0]);                                    // 執行讀取檔案
    } else {
        $("#preview").html('預覽圖');
    }
});

// ------------------送出資料------------------
$("#submit").on('click', function (e) {
    e.preventDefault();

    let pdName = $("#pdName").val().trim();
    let pdPetType = $("#pdPetType").val();
    let pdType = $("#pdType").val();
    let pdStatus = $("#pdStatus").val();
    let pdPrice = $("#pdPrice").val();
    let pdImg = $("#pdImg")[0].files[0];
    let pdInfo = $("#pdInfo").val();

    if(pdName.length === 0 || pdPetType === "" || pdType === "" || pdPrice.length === 0 || !pdImg) {
        Swal.fire({
            icon: 'error',
            title: '齁...',
            text: '欄位不可以空著唷!',
        });
    } else {
        const currentDate = new Date();
        const year = currentDate.getFullYear();
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const day = String(currentDate.getDate()).padStart(2, '0');
        const formattedDate = `${year}-${month}-${day}`;

        data.pdName = pdName;
        data.pdPetType = pdPetType;
        data.pdType = pdType;
        data.pdPrice = pdPrice;
        data.pdStatus = pdStatus;
        data.pdInfo = pdInfo;
        data.pdDate = formattedDate;
        console.log(data);

        axios
            .post("/product/add", data)  // 設定url, object
            .then((res) => {
                console.log(res.data);  // 獲得回傳資料
            })
            .catch((err) => {
                console.error(err);
            });

        location.href = "/news_product/Product.html";

    }

});