$(function(){
    // 初期状態のボタンは無効
    $("#btn1").prop("disabled", true);
      // チェックボックスの状態が変わったら（クリックされたら）
      $("input[type='checkbox']").on('change', function () {
          // チェックされているチェックボックスの数
          if ($(".chk:checked").length == 1) {
            // ボタン有効
            $("#btn1").prop("disabled", false);
              // #btn1要素をクリックしたら発動
              $('#btn1').click(function() {
                // <form>要素を送信
                $('form').submit();
            });
         } else {
                // ボタン無効
                $("#btn1").prop("disabled", true);
            }
        });
    });

