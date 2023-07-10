// ------------------列出資料------------------
$(document).ready(function () {
    const tableBody = document.getElementById('tableBody');
    axios.get("/news/get/all")
        .then( function (res) {
            console.log(res.data);
            const sixData = res.data.slice(0, 6);
            sixData.forEach(element => {
                const row = `
                        <div class="col" style="margin-bottom: 10px">
                            <div class="d-flex">
                              <div
                                class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex flex-shrink-0 justify-content-center align-items-center d-inline-block mb-3 bs-icon"
                                style="background: #fd780f;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em"
                                  fill="currentColor" viewBox="0 0 16 16" class="bi bi-bell-fill" style="font-size: 16px;">
                                  <path
                                    d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z">
                                  </path>
                                </svg></div>
                              <div class="px-3">
                                <h4 style="color: #e69609;margin-bottom: 0px;">${element.newsTitle}</h4>
                                <p style="color: #a67c52;margin-bottom: 10px;">${element.newsDate}</p>
                                <p style="color: #574f36;">${element.newsContent}</p>
                              </div>
                            </div>
                        </div>
                            `
                tableBody.innerHTML += row;
            })
        })

        .catch(err => console.log(err));

})