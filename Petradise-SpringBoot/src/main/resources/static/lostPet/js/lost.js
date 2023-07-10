const btn_create = document.querySelector('.btn-create');
const option_dog = document.querySelector("#dog");
const option_cat = document.querySelector("#cat");
const option_bird = document.querySelector("#bird");
const option_other = document.querySelector("#other");
btn_create.addEventListener('click', () => {
    const targetPageUrl = './lost_post.html';
    window.location.href = targetPageUrl;
});

axios.get("/LostPetArticle/all")
.then(response => {
  const jsonData = response.data;
  const parentContainer = document.querySelector('.row');

  jsonData.forEach(data => {
    const base64Images = data.lostPetPic.map(pic => {
      return `data:image/*;base64,${pic.lostPetPic}`;
    });

    const imageSrc = base64Images.length > 0 ? base64Images[0] : 'https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg';

    const card = document.createElement('div');
    card.classList.add('col-12', 'col-md-6', 'col-lg-3');
    card.innerHTML = `
      <div class="card">
    	<a href="/lostPet/forum.html?id=${data.articleId}">
          <img class="card-img-top" src="${imageSrc}" alt="image">
        </a>
        <div class="card-body">
          <div class="card-title"><a href="/lostPet/forum.html?id=${data.articleId}">${data.title}</a></div>
          <div class="card-text">${data.text}</div>
        </div>
      </div>
    `;
    parentContainer.appendChild(card);
  });
})
.catch(error => {
  console.error(error);
});

$(document).ready(function() {
  $("#speciesSelect").change(function() {
    var selectedValue = $(this).val();
    
    if (selectedValue === "AllType") {
    	const parentContainer = document.querySelector('.row');
    	parentContainer.innerHTML = ''; // 清空
			
    	axios.get("/LostPetArticle/all")
	    .then(response => {
	      const jsonData = response.data;
	      const parentContainer = document.querySelector('.row');
	      console.log(jsonData);

	      jsonData.forEach(data => {
	        const base64Images = data.lostPetPic.map(pic => {
	          return `data:image/*;base64,${pic.lostPetPic}`;
	        });

	        const imageSrc = base64Images.length > 0 ? base64Images[0] : 'https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg';

	        const card = document.createElement('div');
	        card.classList.add('col-12', 'col-md-6', 'col-lg-3');
	        card.innerHTML = `
	          <div class="card">
	        	<a href="/lostPet/forum.html?id=${data.articleId}">
	              <img class="card-img-top" src="${imageSrc}" alt="image">
	            </a>
	            <div class="card-body">
	              <div class="card-title"><a href="/lostPet/forum.html?id=${data.articleId}">${data.title}</a></div>
	              <div class="card-text">${data.text}</div>
	            </div>
	          </div>
	        `;
	        parentContainer.appendChild(card);
	      });
	    })
	    .catch(error => {
	      console.error(error);
	    });
    } else if (selectedValue === "Dogs") {
    	const parentContainer = document.querySelector('.row');
    	parentContainer.innerHTML = ''; // 清空
    	axios
    	.get("/LostPetArticle/selectBySpecies", {
    		params: { species: "狗" },
    	})
    	.then(function (response) {
    		const jsonData = response.data;
    		const parentContainer = document.querySelector(".row");
    		jsonData.forEach((data) => {
    			const base64Images = data.lostPetPic.map((pic) => {
    				return `data:image/*;base64,${pic.lostPetPic}`;
    			});
    			const imageSrc =
    				base64Images.length > 0
    				? base64Images[0]
    				: "https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg";
    				const card = document.createElement("div");
    				card.classList.add("col-12", "col-md-6", "col-lg-3");
    				card.innerHTML = `
	    	          <div class="card">
	    	            <a href="/lostPet/forum.html?id=${data.articleId}">
	    	              <img class="card-img-top" src="${imageSrc}" alt="image">
	    	            </a>
	    	            <div class="card-body">
	    	              <div class="card-title"><a href="/lostPet/forum.html?id=${data.articleId}">${data.title}</a></div>
	    	              <div class="card-text">${data.text}</div>
	    	            </div>
	    	          </div>
	    	        `;
	    	        parentContainer.appendChild(card);
    		});
    	})
    	.catch(function (error) {
    		console.error(error);
    	});
    } else if (selectedValue === "Cats") {
    	const parentContainer = document.querySelector('.row');
    	parentContainer.innerHTML = ''; // 清空
    	axios
	    .get("/LostPetArticle/selectBySpecies", {
	      params: { species: "貓" },
	    })
	    .then(function (response) {
	      const jsonData = response.data;
	      const parentContainer = document.querySelector(".row");
	      jsonData.forEach((data) => {
	        const base64Images = data.lostPetPic.map((pic) => {
	          return `data:image/*;base64,${pic.lostPetPic}`;
	        });
	        const imageSrc =
	          base64Images.length > 0
	            ? base64Images[0]
	            : "https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg";
	        const card = document.createElement("div");
	        card.classList.add("col-12", "col-md-6", "col-lg-3");
	        card.innerHTML = `
	          <div class="card">
	            <a href="/lostPet/forum.html?id=${data.articleId}">
	              <img class="card-img-top" src="${imageSrc}" alt="image">
	            </a>
	            <div class="card-body">
	              <div class="card-title"><a href="/lostPet/forum.html?id=${data.articleId}">${data.title}</a></div>
	              <div class="card-text">${data.text}</div>
	            </div>
	          </div>
	        `;
	        parentContainer.appendChild(card);
	      });
	    })
	    .catch(function (error) {
	      console.error(error);
	    });
    	
    } else if (selectedValue === "Birds") {
    	const parentContainer = document.querySelector('.row');
    	parentContainer.innerHTML = ''; // 清空
    	axios
	    .get("/LostPetArticle/selectBySpecies", {
	      params: { species: "鳥" },
	    })
	    .then(function (response) {
	      const jsonData = response.data;
	      const parentContainer = document.querySelector(".row");
	      jsonData.forEach((data) => {
	        const base64Images = data.lostPetPic.map((pic) => {
	          return `data:image/*;base64,${pic.lostPetPic}`;
	        });
	        const imageSrc =
	          base64Images.length > 0
	            ? base64Images[0]
	            : "https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg";
	        const card = document.createElement("div");
	        card.classList.add("col-12", "col-md-6", "col-lg-3");
	        card.innerHTML = `
	          <div class="card">
	            <a href="/lostPet/forum.html?id=${data.articleId}">
	              <img class="card-img-top" src="${imageSrc}" alt="image">
	            </a>
	            <div class="card-body">
	              <div class="card-title"><a href="/lostPet/forum.html?id=${data.articleId}">${data.title}</a></div>
	              <div class="card-text">${data.text}</div>
	            </div>
	          </div>
	        `;
	        parentContainer.appendChild(card);
	      });
	    })
	    .catch(function (error) {
	      console.error(error);
	    });
    	
    } else if (selectedValue === "Others") {
    	const parentContainer = document.querySelector('.row');
    	parentContainer.innerHTML = ''; // 清空
    	axios
	    .get("/LostPetArticle/selectBySpecies", {
	      params: { species: "其他" },
	    })
	    .then(function (response) {
	      const jsonData = response.data;
	      const parentContainer = document.querySelector(".row");
	      jsonData.forEach((data) => {
	        const base64Images = data.lostPetPic.map((pic) => {
	          return `data:image/*;base64,${pic.lostPetPic}`;
	        });
	        const imageSrc =
	          base64Images.length > 0
	            ? base64Images[0]
	            : "https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg";
	        const card = document.createElement("div");
	        card.classList.add("col-12", "col-md-6", "col-lg-3");
	        card.innerHTML = `
	          <div class="card">
	            <a href="/lostPet/forum.html?id=${data.articleId}">
	              <img class="card-img-top" src="${imageSrc}" alt="image">
	            </a>
	            <div class="card-body">
	              <div class="card-title"><a href="/lostPet/forum.html?id=${data.articleId}">${data.title}</a></div>
	              <div class="card-text">${data.text}</div>
	            </div>
	          </div>
	        `;
	        parentContainer.appendChild(card);
	      });
	    })
	    .catch(function (error) {
	      console.error(error);
	    });
    }
  });
});