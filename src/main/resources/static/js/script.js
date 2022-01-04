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
        alert("Zwyciężył " + data.winnersName + "!");
    }
    else if(data.roundCount > 9){
        alert("Mamy remis!");
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