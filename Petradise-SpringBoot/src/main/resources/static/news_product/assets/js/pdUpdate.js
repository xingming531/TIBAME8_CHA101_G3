const data = {};
// ------------------選單------------------
const selector = {
    "寶可夢": ["寶可夢球", "樹果", "回復道具", "戰鬥道具", "其他"],
    "狗": ["飼料", "罐罐", "零食", "玩具", "保健", "其他"],
    "貓": ["飼料", "罐罐", "零食", "玩具", "保健", "其他"]
}
// $("#pdPetType").on("change", function (e){
//
//     $("#pdType").empty();
//     const pdTypeArr = selector[e.target.value];
//     pdTypeArr.forEach( function (type) {
//         const option = $(`<option>`).val(type).text(type);
//         $("#pdType").append(option);
//     });
// })

// 生成選單選項
function createOptions() {
    const pdPetType = $("#pdPetType").val();
    const pdTypeArr = selector[pdPetType] || [];

    $("#pdType").empty();

    pdTypeArr.forEach(function (type) {
        const option = $("<option>").val(type).text(type);
        $("#pdType").append(option);
    });
}

$("#pdPetType").on("change", function (){
    createOptions();
});

// ------------------拿session資料------------------
$(document).ready(function () {
    const pdId = JSON.parse(sessionStorage.getItem("pdId"));
    // console.log("/product/get/" + pdId)

    axios.get("/product/get/" + pdId, {
        async: false
    })
    .then(function (res) {
        console.log(res.data);
        let base64Img = `data:image/*;base64,${res.data.pdImg}`;
        $("#pdName").val(res.data.pdName);
        $("#pdPetType").val(res.data.pdPetType);
        createOptions();
        $("#pdType").val(res.data.pdType);
        $("#pdStatus").val(res.data.pdStatus);
        $("#pdPrice").val(res.data.pdPrice);
        $("#pdInfo").val(res.data.pdInfo);
        $("#preview").html(`<img src="${base64Img}" alt="image" style="width: 100%">`);

        data.pdImg = `${res.data.pdImg}`;   // 去除base64前墜
    })
    .catch(err => console.log(err));

})

// ------------------修改資料------------------
const pdId = JSON.parse(sessionStorage.getItem("pdId"));
console.log(pdId);

$("#submit").on('click', function (e) {
    e.preventDefault();

    let pdName = $("#pdName").val().trim();
    let pdPetType = $("#pdPetType").val();
    let pdType = $("#pdType").val();
    let pdStatus = $("#pdStatus").val();
    let pdPrice = $("#pdPrice").val();
    let pdInfo = $("#pdInfo").val();

    if(pdName.length === 0 || pdPetType === "" || pdType === "" || pdPrice.length === 0) {
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

        axios
            .put("/product/update/" + pdId, data, {
                headers: {
                    "Content-Type": "application/json"
                }
            })  // 設定url, object
            .then((res) => {
                console.log(res.data);  // 獲得回傳資料
            })
            .catch((err) => {
                console.error(err);
            });

        console.log(data);
        location.href = "/news_product/Product.html";
    }
});

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