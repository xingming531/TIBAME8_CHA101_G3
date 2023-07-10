
const localStorageKey = 'animalCorporationOwner';

function isSignedIn() {
    const vo = localStorage.getItem(localStorageKey);
    return !!vo; // Double negation turns a truthy or falsy value into actual true or false
}

function saveAnimalCorporation(corporation) {
    localStorage.setItem(localStorageKey, JSON.stringify(corporation));
}

function getAnimalCorporation() {
    const corporation = localStorage.getItem(localStorageKey);
    if (!corporation) {
        return null;
    }
    return JSON.parse(corporation);
}


function getAnimalCorporationId() {
    const owner = getAnimalCorporation();
    if (!owner) {
        return null;
    }
    return owner.corpId;
}
function getAnimalCorporationName() {
    const owner = getAnimalCorporation();
    if (!owner) {
        return null;
    }
    return owner.corpName;
}

function redirectToSignin() {
    window.location.href = '/animalCorporation/signin.html?redirect=' + encodeURIComponent(window.location.href);
}

function redirectToIndex() {
    window.location.href = '/';
}

function logout() {
    localStorage.removeItem(localStorageKey);
    redirectToSignin();
}