(() => {

    const petTypeSelect = document.querySelector('#type');
    const petSizeSelect = document.querySelector('#size');

    document.addEventListener('DOMContentLoaded', function() {
        guardIsSignedIn();
        fetchPetOptions();
        createPetPicPreview();
        setupFormSubmit();
    });

    async function fetchPetOptions() {
        try {
            const response = await fetch('/pets/options');
            const options = await response.json();
            const petTypes = options.petType;
            const petSizes = options.petSize;
            populatePetTypeOptions(petTypes);
            populatePetSizeOptions(petSizes);
        } catch (error) {
            console.log(error);
        }
    }

    function populatePetTypeOptions(petTypes) {
        petTypes.forEach(petType => {
            petTypeSelect.appendChild(createPetTypeOption(petType));
        });
    }

    function populatePetSizeOptions(petSizes) {
        petSizes.forEach(petSize => {
            petSizeSelect.appendChild(createPetSizeOption(petSize));
        });
    }

    function createPetTypeOption(petType) {
        const option = document.createElement('option');
        option.value = petType;
        option.textContent = getPetTypeText(petType);
        return option;
    }

    function createPetSizeOption(petSize) {
        const option = document.createElement('option');
        option.value = petSize;
        option.textContent = getPetSizeText(petSize);
        return option;
    }

    function getPetTypeText(petType) {
        switch (petType.toUpperCase()) {
            case 'DOG':
                return '狗';
            case 'CAT':
                return '貓';
            case 'BIRD':
                return '鳥';
            case 'OTHER':
                return '其他';
        }
    }

    function getPetSizeText(petSize) {
        switch (petSize.toUpperCase()) {
            case 'S':
                return '小型';
            case 'M':
                return '中型';
            case 'L':
                return '大型';
        }
    }

    function createPetPicPreview() {
        let petPicsInput = document.querySelector('#petPics');
        let imagePreview = document.querySelector('#imagePreview');

        if (petPicsInput) {
            petPicsInput.addEventListener('change', function(event) {
                let files = event.target.files;
                imagePreview.innerHTML = '';
                Array.from(files).forEach(file => {
                    let reader = new FileReader();
                    reader.onload = function(e) {
                        let img = document.createElement('img');
                        img.src = e.target.result;
                        img.style.maxWidth = '150px';
                        img.style.height = 'auto';
                        img.style.marginRight = '10px';
                        imagePreview.appendChild(img);
                    };
                    reader.readAsDataURL(file);
                })
            });
        }
    }

    function setupFormSubmit() {
        const form = document.querySelector('#form');

        form.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(form);
            const pet = {
                name: formData.get('name'),
                type: formData.get('type'),
                size: formData.get('size'),
                memberId: Number(getMemberId()),
            };
            let petPicPromises = Array.from(formData.getAll('petPics')).map(pic => {
                return new Promise((resolve, reject) => {
                    let fileReader = new FileReader();
                    fileReader.readAsDataURL(pic);
                    fileReader.onload = function(e) {
                        let base64Image = e.target.result.split(',')[1];  // remove MIME type
                        resolve(base64Image);
                    };
                    fileReader.onerror = function(e) {
                        reject(new Error("File reading failed"));
                    };
                });
            });

            Promise.all(petPicPromises)
                .then(results => {
                    const data = { pet, pics: results };
                    return fetch('/pets/add', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(data),
                    });
                })
                .then(response => {
                    if (response.ok) {
                        showSuccessAlert();
                    } else {
                        showErrorAlert(response.statusText);
                    }
                });
        });

    }

    function showSuccessAlert() {
        Swal.fire({
            icon: 'success',
            title: '新增寵物成功',
            showConfirmButton: false,
            timer: 1500,
            didClose: () => {
                window.location.href = '/member/pets.html';
            }
        });
    }

    function showErrorAlert(errorMessage) {
        Swal.fire({
            icon: 'error',
            title: '新增寵物失敗',
            text: errorMessage,
            confirmButtonText: '確認',
        })
    }

})();
