let members = [];

$(document).ready(function () {
    guardIsSignedIn();
    fetchMembers();
});

function fetchMembers() {
    fetch('/members/all')
        .then(response => response.json())
        .then(json => {
            members = json;
            renderTable(json);
        });

}

function renderTable(members) {
    $('#memberTable').DataTable({
        data: members,
        columns: [
            {data: 'id'},
            {data: 'name'},
            {data: 'account'},
            {data: 'birthday'},
            {data: 'phone'},
            {data: 'email'},
            {data: 'address'},
            {
                data: 'access',
                render: function (data, type, row) {
                    return getAccessElement(data, row);
                }
            },
            {
                data: 'isEmailVerified',
                render: function (data, type, row) {
                    return getEmailVerifiedText(data);
                }
            }
        ]
    });
}

function getAccessElement(access, row) {
    return `
            <select class="form-control access-select" onchange="changeAccess(this, ${row.id})">
                <option value="0" ${access === '0' ? 'selected' : ''}>${getAccessText('0')}</option>
                <option value="1" ${access === '1' ? 'selected' : ''}>${getAccessText('1')}</option>
            </select>
        `;
}


function getEmailVerifiedText(isVerified) {
    switch (isVerified) {
        case true:
            return '✅已驗證';
        case false:
            return '❌未驗證';
    }
}


function changeAccess(input, memberId) {
    let access = input.value;

    Swal.fire({
        title: `確定要將會員ID: "${memberId}" 之會員變更權限為"${getAccessText(access)}"嗎？`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '確定',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.isConfirmed) {
            updateAccess(memberId, access);
        } else {
            input.value = getRevertAccess(access);
        }
    })
}

function updateAccess(memberId, access) {
    const data = {
        id: memberId,
        access: access
    }
    fetch(`/members/change-access`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    }).then(response => {
        if (response.ok) {
            Swal.fire({
                title: '權限變更成功',
                icon: 'success',
                confirmButtonText: '確定'
            }).then(() => {
                location.reload();
            });
        } else {
            Swal.fire({
                title: '權限變更失敗',
                icon: 'error',
                confirmButtonText: '確定'
            }).then(() => {
                location.reload();
            });
        }
    });
}

function getRevertAccess(access) {
    switch (access) {
        case '0':
            return '1';
        case '1':
            return '0';
    }
}

function getAccessText(access) {
    switch (access) {
        case '0':
            return '正常';
        case '1':
            return '停權';
    }
}