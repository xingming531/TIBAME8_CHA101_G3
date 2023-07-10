(() => {

    let member;

    document.addEventListener('DOMContentLoaded', function() {
        guardIsSignedIn();
        fetchMember();
    });

    function fetchMember(){
        const memberId = getMemberId();
        const url = `/members/id=${memberId}`;
        fetch(url)
            .then(response => response.json())
            .then(json => member = json)
            .then(() => displayMember(member));
    }
    function displayMember(member) {
        // Populate form with member info
        document.getElementById('name').innerText = member.name;
        document.getElementById('account').innerText = member.account;
        document.getElementById('birthday').innerText = member.birthday;
        document.getElementById('phone').innerText = member.phone;
        document.getElementById('email').innerText = member.email;
        document.getElementById('address').innerText = member.address;
        // Continue for all fields
    }

})();