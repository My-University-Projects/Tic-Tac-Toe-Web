const url = 'http://localhost:8080';
let stompClient;
let gameId;
let playerType;

function connectToSocket(gameId) {

    let socket = new SockJS(url + "/move");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            displayResponse(data);
        })
    })
}

function create_game() {
    let login = document.getElementById("playerName").value;
    if (login == null || login === '') {
        alert("Wprowadź nazwę użytkownika");
    } else {
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "playerName": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = "X";
                reset();
                connectToSocket(gameId);
                alert("Id stworzonej gry: " + data.gameId);
                gameOn = true;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}


function connectToRandom() {
    let login = document.getElementById("playerName").value;
    if (login == null || login === '') {
        alert("Wprowadź nazwę użytkownika");
    } else {
        $.ajax({
            url: url + "/game/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "playerName": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = "O";
                reset();
                connectToSocket(gameId);
                alert("Grasz przeciwko: " + data.player1.playerName);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToSpecificGame() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Wprowadź nazwę użytkownika");
    } else {
        let gameId = document.getElementById("game_id").value;
        if (gameId == null || gameId === '') {
            alert("Wpisz poprawne id gry");
        }
        $.ajax({
            url: url + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": login
                },
                "gameId": gameId
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = "O";
                reset();
                connectToSocket(gameId);
                alert("Grasz przeciwko: " + data.player1.playerName);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}