(() => {

    document.addEventListener('DOMContentLoaded', function() {
        guardIsSignedIn();
        fetchPets();
    });

    function fetchPets() {
        fetch(`/pets/memberId=${getMemberId()}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(displayPets)
            .catch(e => console.error('Fetch failed: ', e));
    }

    function createPetCard(pet) {
        let image = 'https://placehold.co/268x180';
        const petPics = pet.petPics;
        if (petPics !== undefined && petPics.length > 0) {
            image = ` data:image/jpeg;base64,${petPics[0].pic}`;
        }
        return `
        <div class="col-md-4" id="pet-card-${pet.id}">
            <div class="card mb-4">
                <div class="img-container">
                    <img src="${image}" class="card-img-top" alt="${pet.name}">
                </div>
                <div class="card-body d-flex justify-content-between align-items-end">
                    <div>
                        <h5 class="card-title">${pet.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${getPetTypeText(pet.type)}</h6>
                        <p class="card-text">${getPetSizeText(pet.size)}</p>
                    </div>
                    <button class="btn btn-danger delete-btn" data-pet-id="${pet.id}">刪除寵物</button>
                </div>
            </div>
        </div>
    `;
    }



    function createNewPetCard() {
        return `
        <div class="col-md-4">
            <div class="card mb-4 new-pet-card" onClick="window.location.href='/member/add_pet.html'" style="cursor:pointer;">
                <div class="card-body text-center d-flex flex-column justify-content-center">
                    <svg class="mx-auto" width="40" height="40" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M12 6C12.5523 6 13 6.44772 13 7V11H17C17.5523 11 18 11.4477 18 12C18 12.5523 17.5523 13 17 13H13V17C13 17.5523 12.5523 18 12 18C11.4477 18 11 17.5523 11 17V13H7C6.44772 13 6 12.5523 6 12C6 11.4477 6.44772 11 7 11H11V7C11 6.44772 11.4477 6 12 6Z" fill="#888888" />
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M5 22C3.34315 22 2 20.6569 2 19V5C2 3.34315 3.34315 2 5 2H19C20.6569 2 22 3.34315 22 5V19C22 20.6569 20.6569 22 19 22H5ZM4 19C4 19.5523 4.44772 20 5 20H19C19.5523 20 20 19.5523 20 19V5C20 4.44772 19.5523 4 19 4H5C4.44772 4 4 4.44772 4 5V19Z" fill="#888888" />
                    </svg>
                    <h3 class="card-title new-pet-title">新增寵物</h3>
                </div>
            </div>
        </div>
    `;
    }

    function displayPets(pets) {
        const petsRow = document.getElementById('pets-row');

        // Clear existing pet cards
        petsRow.innerHTML = '';

        // Add "New Pet" card
        petsRow.insertAdjacentHTML('afterbegin', createNewPetCard());

        // Display actual pet cards
        pets.forEach(pet => {
            petsRow.insertAdjacentHTML('beforeend', createPetCard(pet));
        });

        // Add event listeners to delete buttons
        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', showDeleteAlert);
        });
    }

    function showDeleteAlert(event) {
        const petId = event.target.dataset.petId;
        Swal.fire({
            title: '確定要刪除寵物嗎？',
            text: '刪除後無法復原',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '確定',
            cancelButtonText: '取消',
        }).then(result => {
            if (result.isConfirmed) {
                deletePet(petId);
            }
        });
    }

    function deletePet(petId) {
        fetch(`/pets/delete/id=${petId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('請確認網路環境正常');
                }
                // Remove the pet card from the UI
                removePetCard(petId);
                showDeleteSuccessAlert(petId);
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: '刪除寵物失敗',
                    text: error.message,
                });
            });
    }


    function removePetCard(petId) {
        let petCard = document.getElementById(`pet-card-${petId}`);
        petCard.classList.add("fade-out"); // Adding the animation class
        setTimeout(() => {
            petCard.remove();
        }, 500); // Set timeout equal to the animation duration
    }

    function showDeleteSuccessAlert(petId) {
        Swal.fire({
            icon: 'success',
            title: '刪除寵物成功',
            showConfirmButton: false,
            timer: 1500,
            didClose: () => {
                // Refresh the pet list after successful deletion
                fetchPets();
            }
        });
    }

    function getPetTypeText(type) {
        switch (type) {
            case 'Dog':
                return '狗';
            case 'Cat':
                return '貓';
            case 'Bird':
                return '鳥';
            case 'Other':
                return '其他';
            default:
                return '未知';
        }
    }

    function getPetSizeText(size) {
        switch (size) {
            case 'S':
                return '小型';
            case 'M':
                return '中型';
            case 'L':
                return '大型';
            default:
                return '未知';
        }
    }


})();
