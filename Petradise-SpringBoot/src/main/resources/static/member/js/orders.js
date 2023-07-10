(() => {
    document.addEventListener('DOMContentLoaded', () => {
        guardIsSignedIn();
        fetchOrderMaster();
    });

    $('#table_body').on('click', '#order_detail_check', function () {
        const orderId = $(this).closest('tr').find('.order_id').data('order-id');
        fetchOrderDetail(orderId);
    })

    function fetchOrderMaster() {
        fetch(`/order/memberIdAndOrderdStatusNot=${getMemberId()}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(onReceivedJSON)
            .catch(error => console.error('There was a problem with the fetch operation', error));
    }

    function onReceivedJSON(jsonData) {
        const tableBody = document.getElementById('table_body');
        console.log(jsonData);
        for (let i of jsonData) {
            const row = `
                <tr>
                    <td class="order_id" data-order-id="${i.odId}" style="color: #a67c52;">${i.odId}</td>
                    <td class="order_date" style="color: #a67c52;">${i.odDate}</td>
                    <td class="price_od" style="color: #a67c52;">${i.priceOd}</td>
                    <td class="reci_name" style="color: #a67c52;">${i.reciName}</td>
                    <td class="reci_phone" style="color: #a67c52;">${i.reciPhone}</td>
                    <td class="order_status" style="color: #a67c52;">${convertOrderStatus(i.odStatus)}</td>

                    <!-- 查看訂單 -->
                    <td class="view_order_work">
                        <!-- Button trigger modal -->
                        <button type="button" class="view_order" id="order_detail_check" data-bs-toggle="modal"
                            data-bs-target="#staticBackdrop">
                            詳情
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="staticBackdrop"
                            data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                            aria-labelledby="staticBackdropLabel" aria-hidden="true">

                            <!-- Modal-dialog -->
                            <div
                                class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h3 class="modal-title" id="staticBackdropLabel">
                                            查看訂單詳情</h3>
                                        <button type="button" class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>

                                    <!-- Light-Box-Body -->
                                    <div class="modal-body">

                                        <table class="table" id="order_detail">

                                        </table>

                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary"
                                            data-bs-dismiss="modal">確定</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>

                    <td>
                        <button type="button" class="btn btn-danger delete_order" id="delete_order" data-order-id="${i.odId}">
                            取消訂單
                        </button>
                    </td>

                </tr>`
                ;
            tableBody.innerHTML += row;
        }
        addDeleteListener();
    }

    function fetchOrderDetail(orderId) {
        fetch(`/order/showOrderDetail/id=${orderId}`, {
            method: 'GET'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error status: ${response.status}`)
                }
                return response.json();
            })
            .then(onReceivedOrderDetailJson)
            .catch(error => console.error('There was a problem with the fetch operation', error));
    }

    function onReceivedOrderDetailJson(orderJsonData) {
        console.log(orderJsonData);
        const orderDetail = document.getElementById('order_detail');
        const productInfo = orderJsonData.map(order => `${order.pdName} x ${order.pdAmount}`).join(' / ');

        const table = `
        <thead>
            <tr>
                <th scope="row" style="color: #a67c52;">訂單編號    ${orderJsonData[0].odId}</th>
                <td id="order_id" scope="col"></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row" style="color: #a67c52;">商品</th>
                <td id="product_name">${productInfo}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">訂購金額</th>
                <td id="price_ori">${orderJsonData[0].priceOri}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">運費</th>
                <td id="price_ship">${orderJsonData[0].priceShip}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">付款總金額</th>
                <td id="price_total">${orderJsonData[0].priceOri + orderJsonData[0].priceShip}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">訂單日期</th>
                <td id="order_date">${orderJsonData[0].odDate}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">訂單狀態</th>
                <td id="order_status">${convertOrderStatus(orderJsonData[0].odStatus)}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">付款方式</th>
                <td id="order_payment">${convertOrderPayment(orderJsonData[0].odPay)}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">取貨方式</th>
                <td id="order_ship">${convertOrderDelivery(orderJsonData[0].odShip)}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">收件人姓名</th>
                <td id="reci_name">${orderJsonData[0].reciName}</td>
            </tr>
            <tr>
                <th scope="row" style="color: #a67c52;">收件人電話</th>
                <td id="reci_phone">${orderJsonData[0].reciPhone}</td>
            </tr>
            ${orderJsonData[0].reciStore ? `
                <tr>
                    <th scope="row" style="color: #a67c52;">收件門市</th>
                    <td id="reci_store">${orderJsonData[0].reciStore}</td>
                </tr>
            ` : ''}
            ${!orderJsonData[0].reciStore ? `
                <tr>
                    <th scope="row" style="color: #a67c52;">收件地址</th>
                    <td id="reci_add">${orderJsonData[0].reciAdd}</td>
                </tr>             
            ` : ''}

        </tbody>
    `;
        orderDetail.innerHTML = table;

    }

    function addDeleteListener() {
        const deleteButton = document.querySelectorAll('#delete_order');
        console.log('delete buttons: ' + deleteButton);
        Array.from(deleteButton).forEach(button => {
            button.addEventListener('click', (e) => {
                // any dash is replaced by the capitalization of the next letter, converting the name to camelcase.
                console.log(e);
                const orderId = button.dataset.orderId; // HTMLElement.dataset.testValue
                showDeleteAlert(orderId);
            });
        });
    }

    function showDeleteAlert(orderId) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(result => {
            if (result.isConfirmed) {
                Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                )
                updateOrderStatus(orderId);

            }
        })
    }

    function updateOrderStatus(orderId) {
        const data = {
            odId: orderId,
            odStatus: '1'
        }

        fetch('/order/updateOrderStatus', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Order status updated seccessfully.');
                    location.reload();
                } else {
                    console.log('Failed to update order status.');
                }
            })
            .catch(error => {
                console.error(error => console.error('There was a problem with the fetch operation', error));
            });
    }

    function convertOrderStatus(odStatus) {
        switch (odStatus) {
            case '0':
                return '訂單成立';
            case '1':
                return '訂單失效';
        }
    }

    function convertOrderPayment(odPay) {
        switch (odPay) {
            case '0':
                return '貨到付款';
            case '1':
                return '信用卡結帳';
            case '2':
                return '匯款轉帳';
        }
    }

    function convertOrderDelivery(odShip) {
        switch (odShip) {
            case '0':
                return '宅配';
            case '1':
                return '711取貨';
            case '2':
                return '全家取貨';
        }
    }

})();


// ===========================================================================================================
// JQuery
// (() => {
//     (() => {
//         $(document).ready(function () {
//             guardIsSignedIn();
//             fetchOrderMaster();
//             deleteOrder();
//         })

//         $('#table_body').on('click', '#order_detail_check', function () {
//             const orderId = $(this).closest('tr').find('.order_id').data('order-id');
//             fetchOrderDetail(orderId);
//         })

//         function fetchOrderMaster() {
//             fetch(`/order/memberIdAndOrderdStatusNot=${getMemberId()}`)
//                 .then(response => {
//                     if (!response.ok) {
//                         throw new Error(`HTTP error! status: ${response.status}`);
//                     }
//                     return response.json();
//                 })
//                 .then(onReceivedJSON)
//                 .catch(error => console.error('There was a problem with the fetch operation', error));
//         }

//         function onReceivedJSON(jsonData) {
//             const tableBody = document.getElementById('table_body');
//             console.log(jsonData);
//             for (let i of jsonData) {
//                 const row = `
//                     <tr>
//                         <td class="order_id" data-order-id="${i.odId}" style="color: #a67c52;">${i.odId}</td>
//                         <td class="order_date" style="color: #a67c52;">${i.odDate}</td>
//                         <td class="price_od" style="color: #a67c52;">${i.priceOd}</td>
//                         <td class="reci_name" style="color: #a67c52;">${i.reciName}</td>
//                         <td class="reci_phone" style="color: #a67c52;">${i.reciPhone}</td>
//                         <td class="order_status" style="color: #a67c52;">${convertOrderStatus(i.odStatus)}</td>

//                         <!-- 查看訂單 -->
//                         <td class="view_order_work">
//                             <!-- Button trigger modal -->
//                             <button type="button" class="view_order" id="order_detail_check" data-bs-toggle="modal"
//                                 data-bs-target="#staticBackdrop">
//                                 詳情
//                             </button>

//                             <!-- Modal -->
//                             <div class="modal fade" id="staticBackdrop"
//                                 data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
//                                 aria-labelledby="staticBackdropLabel" aria-hidden="true">

//                                 <!-- Modal-dialog -->
//                                 <div
//                                     class="modal-dialog modal-dialog modal-dialog-centered modal-dialog-scrollable">
//                                     <div class="modal-content">
//                                         <div class="modal-header">
//                                             <h3 class="modal-title" id="staticBackdropLabel">
//                                                 查看訂單詳情</h3>
//                                             <button type="button" class="btn-close"
//                                                 data-bs-dismiss="modal"
//                                                 aria-label="Close"></button>
//                                         </div>

//                                         <!-- Light-Box-Body -->
//                                         <div class="modal-body">

//                                             <table class="table" id="order_detail">

//                                             </table>

//                                         </div>

//                                         <div class="modal-footer">
//                                             <button type="button" class="btn btn-primary"
//                                                 data-bs-dismiss="modal">確定</button>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                         </td>

//                         <td>
//                             <button type="button" class="btn btn-danger delete_order" data-order-id="${i.odId}">
//                                 取消訂單
//                             </button>
//                         </td>

//                     </tr>`
//                     ;
//                 tableBody.innerHTML += row;
//             }
//         }

//         function fetchOrderDetail(orderId) {
//             fetch(`/order/showOrderDetail/id=${orderId}`, {
//                 method: 'GET'
//             })
//                 .then(response => {
//                     if (!response.ok) {
//                         throw new Error(`HTTP error status: ${response.status}`)
//                     }
//                     return response.json();
//                 })
//                 .then(onReceivedOrderDetailJson)
//                 .catch(error => console.error('There was a problem with the fetch operation', error));
//         }

//         function onReceivedOrderDetailJson(orderJsonData) {
//             console.log(orderJsonData);
//             const orderDetail = document.getElementById('order_detail');
//             const productInfo = orderJsonData.map(order => `${order.pdName} x ${order.pdAmount}`).join(' / ');

//             const table = `
//             <thead>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">訂單編號    ${orderJsonData[0].odId}</th>
//                     <td id="order_id" scope="col"></td>
//                 </tr>
//             </thead>
//             <tbody>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">商品</th>
//                     <td id="product_name">${productInfo}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">訂購金額</th>
//                     <td id="price_ori">${orderJsonData[0].priceOri}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">運費</th>
//                     <td id="price_ship">${orderJsonData[0].priceShip}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">付款總金額</th>
//                     <td id="price_total">${orderJsonData[0].priceOri + orderJsonData[0].priceShip}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">訂單日期</th>
//                     <td id="order_date">${orderJsonData[0].odDate}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">訂單狀態</th>
//                     <td id="order_status">${convertOrderStatus(orderJsonData[0].odStatus)}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">付款方式</th>
//                     <td id="order_payment">${convertOrderPayment(orderJsonData[0].odPay)}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">取貨方式</th>
//                     <td id="order_ship">${convertOrderDelivery(orderJsonData[0].odShip)}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">收件人姓名</th>
//                     <td id="reci_name">${orderJsonData[0].reciName}</td>
//                 </tr>
//                 <tr>
//                     <th scope="row" style="color: #a67c52;">收件人電話</th>
//                     <td id="reci_phone">${orderJsonData[0].reciPhone}</td>
//                 </tr>
//                 ${orderJsonData[0].reciStore ? `
//                     <tr>
//                         <th scope="row" style="color: #a67c52;">收件門市</th>
//                         <td id="reci_store">${orderJsonData[0].reciStore}</td>
//                     </tr>
//                 ` : ''}
//                 ${!orderJsonData[0].reciStore ? `
//                     <tr>
//                         <th scope="row" style="color: #a67c52;">收件地址</th>
//                         <td id="reci_add">${orderJsonData[0].reciAdd}</td>
//                     </tr>
//                 ` : ''}

//             </tbody>
//         `;
//             orderDetail.innerHTML = table;

//         }

//         function deleteOrder() {
//             const deleteButton = document.querySelector('.delete_order');
//             deleteButton.addEventListener('click', showDeleteAlert());
//         }

//         function showDeleteAlert(event) {
//             // any dash is replaced by the capitalization of the next letter, converting the name to camelcase.
//             const orderId = event.target.datadet.orderId; // HTMLElement.dataset.testValue
//             Swal.fire({
//                 title: 'Are you sure?',
//                 text: "You won't be able to revert this!",
//                 icon: 'warning',
//                 showCancelButton: true,
//                 confirmButtonColor: '#3085d6',
//                 cancelButtonColor: '#d33',
//                 confirmButtonText: 'Yes, delete it!'
//             }).then((result) => {
//                 if (result.isConfirmed) {
//                     Swal.fire(
//                         'Deleted!',
//                         'Your file has been deleted.',
//                         'success'
//                     )
//                     displayNoneOrder();
//                 }
//             })
//         }

//         function displayNoneOrder() {

//         }


//         function convertOrderStatus(odStatus) {
//             switch (odStatus) {
//                 case '0':
//                     return '待結帳';
//                 case '1':
//                     return '訂單失效';
//                 case '2':
//                     return '訂單成立';
//                 case '3':
//                     return '備貨中';
//                 case '4':
//                     return '已出貨';
//                 case '5':
//                     return '已送達';
//                 case '6':
//                     return '訂單完成';
//             }
//         }

//         function convertOrderPayment(odPay) {
//             switch (odPay) {
//                 case '0':
//                     return '貨到付款';
//                 case '1':
//                     return '信用卡結帳';
//                 case '2':
//                     return '匯款轉帳';
//             }
//         }

//         function convertOrderDelivery(odShip) {
//             switch (odShip) {
//                 case '0':
//                     return '宅配';
//                 case '1':
//                     return '711取貨';
//                 case '2':
//                     return '全家取貨';
//             }
//         }

//         function initDataTable() {
//             $('#my_table').DataTable({
//                 // JSON cannot use ''
//                 "language": {
//                     "lengthMenu": "顯示 _MENU_ 筆訂單",
//                     "zeroRecords": "沒有符合的結果",
//                     "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
//                     "infoEmpty": "共 0 項",
//                     "infoFiltered": "(從 _MAX_ 條資料中過濾)",
//                     "search": "搜尋:",
//                     "paginate": {
//                         "next": ">",
//                         "previous": "<",
//                         "first": "第一頁",
//                         "last": "最後一頁"
//                     },
//                     "aria": {
//                         "sortAscending": ": 升冪排列",
//                         "sortDescending": ": 降冪排列"
//                     }
//                 }
//             });
//         }
//     })();
// })();