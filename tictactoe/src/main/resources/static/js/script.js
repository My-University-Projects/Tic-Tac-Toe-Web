var turns = [["", "", ""], ["", "", ""], ["", "", ""]];
var turn = "";
var gameOn = false;

function playerTurn(turn, id) {
    if (gameOn) {
        var spotTaken = $("#" + id).text();
        if (spotTaken === "") {
            makeAMove(playerType, id);
        }
    }
}

function makeAMove(type, position) {
    $.ajax({
        url: url + "/game/move",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": playerType,
            "position": position,
            "gameId": gameId
        }),
        success: function (data) {
            gameOn = false;
            displayResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {
    let board = data.board.board;
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board[i].length; j++) {
            if (board[i][j] === "X") {
                turns[i][j] = "X"
            } else if (board[i][j] === "O") {
                turns[i][j] = "O";
            }
            let id = (3 * i) + j + 1;
            $("#" + id).text(turns[i][j]);
        }
    }
    if (data.winnersName != null) {
        alert("The winner is " + data.winnersName + "!");
    }
    else if(data.roundCount > 9){
        alert("Draw!");
    }
    gameOn = true;
}

$(".tic").click(function () {
    var slot = $(this).attr('id');
    playerTurn(turn, slot);
});

function reset() {
    turns = [["", "", ""],
             ["", "", ""],
             ["", "", ""]];
    $(".tic").text("");
}

$("#reset").click(function () {
    reset();
});

function displayTable() {
    var id;
    $.ajax({
        cache: false,
        type: "GET",
        url: "http://localhost:8080" + "/gamestable/table",
        data: { "id": id },
        success: function (data) {
            var result = "";
            //tmp.html('');
            $.each(data, function (id, gameTableRow) {

                result +=  '<tr>' +
                                '<th scope="row">' + gameTableRow.id.toString() + '</th>' +
                    '<td>'
                    + gameTableRow.firstPlayerName.toString() + '</td>'
                    + '<td>' + gameTableRow.secondPlayerName.toString() + '</td>' + '<td>' +
                     gameTableRow.gameStatus.toString() + '</td></tr>'

            });
            $(result).appendTo("#list tbody");
        },

        error: function (xhr, ajaxOptions, thrownError) {

            alert('There are no games started yet');

        }

    });
}

function fillIn(){
        cookies = document.cookie;
        document.getElementById("playerName").value = cookies;
}