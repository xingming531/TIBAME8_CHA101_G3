
const localStorageKey = 'hotelOwner';

function isSignedIn() {
    const ownerId = localStorage.getItem(localStorageKey);
    return !!ownerId; // Double negation turns a truthy or falsy value into actual true or false
}

function saveHotelOwner(owner) {
    localStorage.setItem(localStorageKey, JSON.stringify(owner));
}

function getHotelOwner() {
    const owner = localStorage.getItem(localStorageKey);
    if (!owner) {
        return null;
    }
    return JSON.parse(owner);
}

function getHotelOwnerName() {
    const owner = getHotelOwner();
    if (!owner) {
        return null;
    }
    return owner.hotelName;
}

function getHotelOwnerId() {
    const owner = getHotelOwner();
    if (!owner) {
        return null;
    }
    return owner.hotelId;
}

function redirectToSignin() {
    window.location.href = '/Owner/signin.html?redirect=' + encodeURIComponent(window.location.href);
}

function redirectToIndex() {
    window.location.href = '/';
}

function logout() {
    localStorage.removeItem(localStorageKey);
    redirectToSignin();
}