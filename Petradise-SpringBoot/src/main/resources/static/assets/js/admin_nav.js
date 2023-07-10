(() => {
    document.addEventListener('DOMContentLoaded', function () {
        renderAdminName();
        addLogoutButtonListener();
    });

    function renderAdminName() {
        document.getElementById('admin-name').innerText = getAdmin().name;
    }

    function addLogoutButtonListener() {
        document.getElementById('logout-button').addEventListener('click', function () {
            logout();
        });
    }

})();