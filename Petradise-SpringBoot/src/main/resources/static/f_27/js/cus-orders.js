(() => {
    const ordersSection = $('#orders-section');

    $(document).ready(function () {
        guardIsSignedIn();
        addSearchListener();
        fetchRoomOrders();
    });

    // Function to update the order count
    function updateOrderCount() {
        const visibleOrders = $('.order-item:visible').length;

        if (visibleOrders === 0) {
            $('#dataTable_info').text(`顯示 0 筆，共 0 筆訂單`);
        } else {
            $('#dataTable_info').text(`顯示 1 到 ${visibleOrders} 筆，共 ${visibleOrders} 筆訂單`);
        }
    }

    // Function to filter orders based on the selected order status and search input
    function filterOrders() {
        const selectedStatus = $('.form-select').val();
        const searchValue = $('#order-search-input').val().trim().toLowerCase();
        let matchedOrders = 0;

        $('.order-item').each(function () {
            const orderStatus = $(this).find('#order-status').text().toLowerCase();
            const orderId = $(this).find('#order-id').text().toLowerCase();

            if ((selectedStatus === '全部' || selectedStatus === orderStatus) &&
                (searchValue === '' || orderId.includes(searchValue))) {
                $(this).show();
                matchedOrders++;
            } else {
                $(this).hide();
            }
        });

        if (matchedOrders === 0) {
            $('#no-matches-message').show();
        } else {
            $('#no-matches-message').hide();
        }

        updateOrderCount();
    }

    // Review function
    function initReviewFunctionality(orderId) {
        $(`#review-form-${orderId}`).submit(function (event) {
            event.preventDefault();
            $(`.toggle-button-${orderId}`).text("感謝您的評論！");
            $(`.toggle-button-${orderId}`).removeClass("btn-primary").addClass("btn-warning");
            // Additional code to disable form elements if needed
        });

        // JavaScript code to update the star count dynamically on hover and persist after clicking
        const starLabels = document.querySelectorAll(`.rating-${orderId} > label`);
        const starCount = document.querySelector(`#star-count-${orderId}`);
        const publishButton = document.querySelector(`#publish-button-${orderId}`);
        const textArea = document.querySelector(`#new-review-${orderId}`);
        const form = document.querySelector(`#review-form-${orderId}`);

        let selectedCount = 5; // Set the initial selected count to 5
        starCount.textContent = `(${selectedCount})`; // Display the default selected count initially

        starLabels.forEach((label) => {
            label.addEventListener('mouseover', () => {
                const count = label.getAttribute('title').split(' - ')[1].split(' ')[0];
                starCount.textContent = `(${count})`;
            });

            label.addEventListener('click', () => {
                const count = parseFloat(label.getAttribute('title').split(' - ')[1].split(' ')[0]);
                selectedCount = count;
                starCount.textContent = `(${selectedCount})`;
            });

            label.addEventListener('mouseout', () => {
                starCount.textContent = `(${selectedCount})`; // Display the selected count when the mouse leaves
            });
        });

        form.addEventListener('submit', (event) => {
            event.preventDefault();
            const reviewText = textArea.value;
            const roomOrderId = orderId;

            // Fetch the hotel ID from the server
            fetch(`/room-order/get/hotel-id/${roomOrderId}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Failed to get hotel ID');
                    }
                })
                .then(hotelId => {
                    hotelId = parseInt(hotelId); // Parse the response as an integer
                    var myHeaders = new Headers();
                    myHeaders.append("Content-Type", "application/json");

                    var raw = JSON.stringify({
                        "hotelId": hotelId,
                        "roomOrderId": roomOrderId,
                        "score": selectedCount,
                        "content": reviewText
                    });

                    var requestOptions = {
                        method: 'POST',
                        headers: myHeaders,
                        body: raw,
                        redirect: 'follow'
                    };

                    fetch("/roomreview/add-review", requestOptions)
                        .then(response => response.text())
                        .then(result => console.log(result))
                        .catch(error => console.log('error', error));

                    Swal.fire({
                        title: '感謝您的評論！',
                        icon: 'success',
                    });

                    publishButton.style.display = "none";
                    textArea.setAttribute("disabled", '');
                    starLabels.forEach((label) => {
                        label.style.pointerEvents = 'none';
                    });
                })
        });
    }

    function fetchHotelName(id) {
        $.ajax({
            url: `/room-order/hotel-name/${id}`,
            method: 'GET',
            success: function (name) {
                onReceivedHotelName(id, name);
            },
            error: function () {
                console.log('Error loading hotel name for Order ID:', `${id}`);
            }
        });
    }

    function onReceivedHotelName(id, name) {
        $(`#hotel-name-${id}`).text(name);
    }

    function fetchRoomOrders() {
        const memberId = getMemberId();
        $.ajax({
            url: `/room-order/customer/mem-id/${memberId}`,
            method: 'GET',
            success: onReceivedOrders,
            error: function () {
                $('#orders-section').html('<p>Error loading orders.</p>');
            }
        });
    }

    function onReceivedOrders(orders) {
        emptyOrderSection();
        renderOrders(orders);
    }

    function renderOrders(orders) {
        orders.forEach(function (order) {
            const statusText = getStatusText(order.status);
            const cancelOrderButton = getCancelButton(order.id, order.status);
            const reviewButton = getReviewButton(order.id, order.status);
            // Make an AJAX request to get the hotel name based on the orderId
            fetchHotelName(order.id);
            const html = createOrderCard(order, statusText, cancelOrderButton, reviewButton);
            ordersSection.append(html);
            updateOrderCount();
            addOrderDetailListener(order.id);
            addReviewButtonListener(order.id);
            addCancelButtonListener(order.id);
            // Initialize the review functionality for the specific order
            initReviewFunctionality(order.id);
        });
    }

    function createOrderCard(order, statusText, reviewButton, cancelOrderButton) {
        return `<div class="container bg-secondary bg-opacity-10 border border-2 rounded py-4 py-xl-5 order-item" style="height: 550px;">
                            <div class="row gy-4 gy-md-0">
                                <div class="col-md-6 text-center text-md-start d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-md-start align-items-md-center justify-content-xl-center">
                                    <div style="max-width: 350px;">
                                        <h5><span id="order-status" class="status-${order.status}">${statusText}</span></h5>
                                        <h2 id="hotel-name-${order.id}" class="text-uppercase fw-bold"></h2>
                                        <p>訂單編號: <span id="order-id">${order.id}</span></p>
                                        <p>入住日期: <span id="check-in-date">${order.checkInDate}</span></p>
                                        <p>退房日期: <span id="check-out-date">${order.checkOutDate}</span></p>
                                        <br>
                                        <a class="btn btn-primary btn-lg me-2" id="order-detail-button-${order.id}" role="button">查看詳情</a>
                                        ${reviewButton}
                                        ${cancelOrderButton}
                                        <br><br>
                                        <div class="review-box" style="display: none;">
                                            <fieldset class="rating rating-${order.id}">
                                                <input type="radio" id="star5-${order.id}" name="rating-${order.id}" value="5" checked="checked" />
                                                <label class="full" for="star5-${order.id}" title="Awesome - 5 stars"></label>
                                                <input type="radio" id="star4-${order.id}" name="rating-${order.id}" value="4" />
                                                <label class="full" for="star4-${order.id}" title="Pretty good - 4 stars"></label>
                                                <input type="radio" id="star3-${order.id}" name="rating-${order.id}" value="3" />
                                                <label class="full" for="star3-${order.id}" title="Meh - 3 stars"></label>
                                                <input type="radio" id="star2-${order.id}" name="rating-${order.id}" value="2" />
                                                <label class="full" for="star2-${order.id}" title="Kinda bad - 2 stars"></label>
                                                <input type="radio" id="star1-${order.id}" name="rating-${order.id}" value="1" />
                                                <label class="full" for="star1-${order.id}" title="Sucks big time - 1 star"></label>
                                            </fieldset>
                                            <span id="star-count-${order.id}" class="star-count-class"></span><br>
                                            <div class="col-md-6">
                                                <form id="review-form-${order.id}" accept-charset="UTF-8">
                                                    <textarea class="review-form" rows="4" cols="50" id="new-review-${order.id}" name="comment" placeholder="Enter your review here..." rows="5"></textarea>
                                                    <div class="text-right">
                                                        <button class="btn-indie-trans" id="publish-button-${order.id}" type="submit">Publish</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="p-xl-5 m-xl-5">
                                        <img class="rounded img-fluid" style="min-height: 300px;" src="/room-order/${order.id}/room-picture" onerror="this.onerror=null;this.src='../assets/img/logo-only.png';" alt="Room Picture">
                                    </div>
                                </div>
                            </div>
                        </div>
                        `;
    }

    function addCancelButtonListener(id) {
        // Cancel the order when the "取消訂單" button is clicked
        ordersSection.on('click', `#cancel-order-button-${id}`, function () {
            console.log('Clicked "取消訂單" for Order ID:', `${id}`);

            // Show a confirmation dialog
            Swal.fire({
                title: 'Sure about that?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#dd6b55',
                cancelButtonColor: '#aaa',
                cancelButtonText: '再想一下下',
                confirmButtonText: '確認取消'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Remove the "取消訂單" button
                    $(this).remove();

                    // Call the API to change the order status
                    const apiUrl = `/room-order/id/${id}/change-status`;

                    fetch(apiUrl, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ status: '2' })
                    })
                        .then(response => response.json())
                        .then(data => {
                            console.log('Order status updated:', data);
                            // Perform your additional action here
                            console.log('Performing a new action for Order ID:', `${id}`);

                            Swal.fire(
                                'Cancelled!',
                                'Your order has been canceled.',
                                'success'
                            ).then((result) => {
                                if (result.dismiss === Swal.DismissReason.backdrop || Swal.DismissReason.close) {
                                    location.reload();
                                }
                            });
                        })
                        .catch(error => {
                            console.error('Error updating order status:', error);
                            Swal.fire(
                                'Error!',
                                'Failed to cancel the order.',
                                'error'
                            );
                        });
                }
            });
        });
    }

    function addReviewButtonListener(id) {
        // Leaving review button
        ordersSection.on('click', `#review-button-${id}`, function () {
            console.log('Clicked "留下評論" for Order ID:', `${id}`);

            // Find the corresponding review box and toggle its visibility
            $(this).closest('.order-item').find('.review-box').slideToggle();


        });
    }

    function addOrderDetailListener(id) {
        // Open a new page with the order ID from the URL 查看詳情
        ordersSection.on('click', `#order-detail-button-${id}`, function () {
            console.log('Clicked "查看詳情" for Order ID:', `${id}`);
            // Construct the URL for the new page
            const orderDetailUrl = `/f_27/cus-order-detail.html?id=${id}`;
            // Open the new page
            window.open(orderDetailUrl, '_blank');
        });
    }

    function addSearchListener() {
        // Call the filterOrders function when the order status or search input changes
        $('.form-select, #order-search-input').on('change input', filterOrders);
    }

    function emptyOrderSection() {
        $('#orders-section').empty();
    }

    function getStatusText(status) {
        let statusText = '';
        if (status === '0') {
            statusText = '即將到來';
        } else if (status === '1') {
            statusText = '已完成';
        } else if (status === '2') {
            statusText = '已取消';
        }
        return statusText;
    }

    function getCancelButton(id, status) {
        let cancelButton = '';
        // Display the "取消訂單" button based on the order status
        if (status === '0') {
            cancelButton = `<a class="btn btn-danger btn-sm" id="cancel-order-button-${id}" role="button">取消訂單</a>`;
        } else {
            cancelButton = ''; // Empty string if status is not '0'
        }
        return cancelButton;
    }

    function getReviewButton(id, status) {
        // Display the "留下評論" button only for orders with status code 1 (已完成)
        if (status === '1') {
            return `<a class="btn btn-primary btn-lg me-2 toggle-button-${id}" id="review-button-${id}" role="button">留下評論</a>`;
        } else {
            return ''; // Empty string if status is not 1 (已完成)
        }
    }

})();
