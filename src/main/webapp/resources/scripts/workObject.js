/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function testtest(text) {
    $.ajax({
        url: 'test',
        type: "POST",
        data: { text: text.objects[0].client.description },
        success: function() {
            console.log("OK");
        },
        error: function(err) {
            console.error(err);
        }
    })
}

