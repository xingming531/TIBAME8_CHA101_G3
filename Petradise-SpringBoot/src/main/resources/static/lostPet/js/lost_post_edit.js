$(document).ready(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const id = parseInt(urlParams.get('id'), 10);
  ajaxToGetValue(id);
  guardIsSignedIn();
  convertToBase64();
  displayImages();
  deleteImage(index);
  deletePreImage(index);
  phoneValid();
  asyncSubmit(id);
});

const output1 = document.querySelector(".output1");
const output2 = document.querySelector(".output2");
const input = document.querySelector(".input");

let imagesArray = [];
let numberInput = document.getElementById('contactPhone');
let base64Image = "";
let jsonData = [];
let resLostPetPic = [];
let index;

function ajaxToGetValue(id) {
  $.ajax({
    url: `/LostPetArticle/id=${id}`,
    method: 'GET',
    dataType: 'json',
    success: res => {
      let html = "";
      $("#title").val(res.title);
      const originalDate = res.lostDate;
      const parsedDate = new Date(originalDate);
      const formattedDate = parsedDate.toISOString().slice(0, 16);
      $("#datetime").val(formattedDate);
      $("#lostPlace").val(res.lostPlace);
      $("#chipNum").val(res.chipNum);
      $("#select-animal").val(res.species);
      $("#color").val(res.color);
      $("#feature").val(res.feature);
      $("#text").val(res.text);
      $("#contactPhone").val(res.contactPhone);
      for (let i = 0; i < res.lostPetPic.length; i++) {
        resLostPetPic.push({
          lostPetPicId: res.lostPetPic[i].lostPetPicId,
          lostPetPic: res.lostPetPic[i].lostPetPic
        });
      }
      getPreImages();
    },
    error: err => {
      console.log(err);
      window.location.assign("/lostPet/forum_deleted.html");
    },
  });
}


function getPreImages() {
  let images = "";
  resLostPetPic.forEach((image, index) => {
    images += `<div class="image">
      <img src="data:image/*;base64,${image.lostPetPic}" alt="image">
      <span onclick="deletePreImage(${index})">&times;</span>
    </div>`;
  });
  output1.innerHTML = images;
}


function convertToBase64() {
  input.addEventListener("change", async () => {
	
    const files = input.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      const base64Image = await readFileAsDataURL(file);
      const base64Data = base64Image.split(",")[1]; // 去除前缀
      imagesArray.push({
        lostPetPic: base64Data
      });
    }
    displayImages();
  });
}


function displayImages() {
  let images = "";
  imagesArray.forEach((image, index) => {
    images += `<div class="image">
      <img src="data:image/*;base64,${image.lostPetPic}" alt="image">
      <span onclick="deleteImage(${index})">&times;</span>
    </div>`;
  });
  output2.innerHTML = images;
}

function deleteImage(index) {
  imagesArray.splice(index, 1);
  displayImages();
}

function deletePreImage(index) {
  resLostPetPic.splice(index, 1);
  getPreImages();
}

function phoneValid() {
  numberInput.addEventListener('input', function (event) {
    let inputValue = event.target.value;
    if (!/^\d*$/.test(inputValue)) {
      event.target.value = inputValue.replace(/\D/g, '');
      Swal.fire({
        icon: 'error',
        title: '請輸入數字',
      });
    }
  });
}

function readFileAsDataURL(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onload = (event) => resolve(event.target.result);
    reader.onerror = (error) => reject(error);
    reader.readAsDataURL(file);
  });
}

function asyncSubmit(id) {
  async function submitForm() {
    resLostPetPic = resLostPetPic.concat(imagesArray);

    const articleData = {
      articleId: id,
      memId: getMemberId(),
      lostDate: $("#datetime").val(),
      lostPlace: $("#lostPlace").val(),
      chipNum: $("#chipNum").val(),
      species: $("#select-animal").val(),
      color: $("#color").val(),
      feature: $("#feature").val(),
      text: $("#text").val(),
      contactPhone: $("#contactPhone").val(),
      title: $("#title").val(),
      lostPetPic: resLostPetPic,
    };

    $.ajax({
      url: "/LostPetArticle/update",
      type: "POST",
      data: JSON.stringify(articleData),
      contentType: "application/json",
    })
      .then(function (response) {
        window.location.assign("/lostPet/forum.html?id=" + id);
      })
      .catch(function (error) {
        console.error(error);
      });
  }

  $(".btn-submit").click(function (event) {
    event.preventDefault();
    submitForm();
  });
}
