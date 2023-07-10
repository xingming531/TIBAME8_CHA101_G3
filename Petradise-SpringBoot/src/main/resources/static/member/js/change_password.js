(() => {
    const oldPasswordInput = document.getElementById("oldPassword");
    const newPasswordInput = document.getElementById("newPassword");
    const confirmPasswordInput = document.getElementById("confirmPassword");

    document.addEventListener("DOMContentLoaded", function() {
        guardIsSignedIn();
        preventSpaceInput();
        form.addEventListener("submit", handleSubmit);
    });

    function preventSpaceInput() {
        const inputElements = document.querySelectorAll('input');
        inputElements.forEach(input => {
            input.addEventListener('keypress', function (e) {
                if (e.key === ' ') {
                    e.preventDefault();
                }
            });
        });
    }

    function handleSubmit(event) {
        event.preventDefault();
        if (validateForm()) {
            changePassword(oldPasswordInput.value, newPasswordInput.value);
        }
    }

    function validateForm() {
        // Initialization
        let isFormValid = true;

        // Input validations
        const validations = [
            [oldPasswordInput, '請輸入密碼'],
            [newPasswordInput, '請輸入新密碼'],
            [confirmPasswordInput, '請輸入確認新密碼'],
        ];

        for(const [inputElement, errorMessage, regex] of validations) {
            if (!validateInputElement(inputElement, errorMessage, regex)) {
                isFormValid = false;
                inputElement.focus();
                return isFormValid;
            }
        }

        // Check if new password and confirm password are the same
        if (newPasswordInput.value !== confirmPasswordInput.value) {
            showInvalidFeedback(confirmPasswordInput, '新密碼和確認密碼不一致');
            isFormValid = false;
            confirmPasswordInput.focus();
        }

        // Check if new password and old password are NOT the same
        if (oldPasswordInput.value !== null && oldPasswordInput.value === newPasswordInput.value) {
            showInvalidFeedback(newPasswordInput, '新密碼不可與原先密碼相同！');
            isFormValid = false;
            confirmPasswordInput.focus();
        }

        return isFormValid;
    }

    function validateInputElement(inputElement, errorMessage, regex = null) {
        if (inputElement.value === '' || (regex && !regex.test(inputElement.value))) {
            showInvalidFeedback(inputElement, errorMessage);
            return false;
        } else {
            removeInvalidFeedback(inputElement);
            return true;
        }
    }

    function showInvalidFeedback(inputElement, errorMessage) {
        inputElement.classList.add('is-invalid');
        const existingFeedbackElement = inputElement.parentNode.querySelector('.invalid-feedback');

        if (existingFeedbackElement) {
            existingFeedbackElement.textContent = errorMessage;
        } else {
            const newFeedbackElement = document.createElement('div');
            newFeedbackElement.classList.add('invalid-feedback');
            newFeedbackElement.textContent = errorMessage;
            inputElement.parentNode.appendChild(newFeedbackElement);
        }
    }

    function removeInvalidFeedback(inputElement) {
        inputElement.classList.remove('is-invalid');
        const feedbackElement = inputElement.parentNode.querySelector('.invalid-feedback');
        if (feedbackElement) {
            feedbackElement.remove();
        }
    }


    function changePassword(oldPassword, newPassword) {
        const memberId = getMemberId();
        fetch('/members/change-password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: memberId,
                oldPassword: oldPassword,
                newPassword: newPassword
            })
        }).then(response => {
            if (response.ok) {
                showSuccessAlert(logout);
            } else {
                response.text().then(errorMessage => {
                    showErrorAlert(errorMessage);
                });
            }
        })
    }

    function showSuccessAlert(action) {
        Swal.fire({
            icon: 'success',
            title: '密碼修改成功',
            text: '請重新登入',
            confirmButtonText: '確認',
            allowOutsideClick: false
        }).then((result) => {
            if (result.isConfirmed) {
                action();
            }
        })
    }

    function showErrorAlert(errorMessage) {
        Swal.fire({
            icon: 'error',
            title: '密碼修改失敗',
            text: errorMessage,
            confirmButtonText: '確認',
        })
    }
})();


