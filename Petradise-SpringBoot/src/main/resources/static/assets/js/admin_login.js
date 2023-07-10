
const sessionStorageKey = 'admin';

function guardIsSignedIn() {
    if (isSignedIn() === false) {
        redirectToLogin();
    }
}

function isSignedIn() {
    const vo = sessionStorage.getItem(sessionStorageKey);
    return !!vo; // Double negation turns a truthy or falsy value into actual true or false
}

function saveAdmin(admin) {
    sessionStorage.setItem(sessionStorageKey, JSON.stringify(admin));
}

function getAdmin() {
    const admin = sessionStorage.getItem(sessionStorageKey);
    if (!admin) {
        return null;
    }
    return JSON.parse(admin);
}


function getAdminId() {
    const admin = getAdmin();
    if (!admin) {
        return null;
    }
    return admin.id;
}

function redirectToLogin(redirectUrl) {
    if (redirectUrl) {
        window.location.href = '/admin/signin.html?redirect=' + encodeURIComponent(redirectUrl);
        return;
    }
    window.location.href = '/admin/signin.html?redirect=' + encodeURIComponent(window.location.href);
}

function redirectToIndex() {
    // 實作登入後畫面
    window.location.href = '/f_27/admin-table.html';
}

function logout() {
    sessionStorage.removeItem(sessionStorageKey);
    window.location.href = '/admin/signin.html';
}