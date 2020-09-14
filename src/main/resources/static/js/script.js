$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var descricao = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricao + '</strong>?');
	
});

$(function() {
	//	Adiciona o tooltip de legenda dos botões
	$('[rel="tooltip"]').tooltip();
	
	//	Adiciona máscara monetária
	$('.js-money').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	//	Recebe um título e atualiza no banco de dados
	$('.js-atualizar-status').on('click', function(event){
		//	Para o comportamento padrão do link
		event.preventDefault();
		
		var button = $(event.currentTarget);
		var urlReceber = button.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'GET'
		});
		
		response.done(function(e){
			var id = button.data('codigo');
			$('[data-role=' + id + ']').html('<span class="label label-success">' + e + '</span>');
			button.hide();
		});
		
		response.fail(function(e){
			console.log(e);
			alert("Erro ao receber título");
		});
	});
});