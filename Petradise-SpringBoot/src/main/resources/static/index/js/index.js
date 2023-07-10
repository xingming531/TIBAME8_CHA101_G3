$(document).ready(function () {
    const signedIn = isSignedIn();
    setupMemberElements(signedIn);
    setupNavigation(signedIn);
    setupHotelOwnerSignUpNavigation();
    $('.logout').on('click', logout);
})

function setupNavigation(isSignedIn) {
    setupMemberCenterNavigation(isSignedIn);
    setupSigninNavigation();
}

function setupMemberElements(isSignedIn) {
    if (isSignedIn) {
        $('.navigate-signin')
            .remove();
        return;
    }

    $('.navigate-member-center')
        .remove();
    $('.logout')
        .remove();

}

function setupMemberCenterNavigation(isSignedIn) {
    $('.navigate-member-center')
        .attr('href', '/member/profile.html');
}

function setupSigninNavigation() {
    $('.navigate-signin')
        .attr('href', `/member/signin.html?redirect=${encodeURIComponent(window.location.href)}`);
}

//毛孩旅館 加入我們
function setupHotelOwnerSignUpNavigation(){
	$('.join-hotelowner')
		.attr('href','/owner/SignUp.html')
}