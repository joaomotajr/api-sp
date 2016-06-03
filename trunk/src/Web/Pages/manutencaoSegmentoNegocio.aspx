<%@ Page Language="C#" masterpagefile="../../../../_catalogs/masterpage/theme-api.master" title="2 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>

<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">
<html>
	<head>
<meta name="WebPartPageExpansion" content="full" />
</head>
	<body>
		<div id="angularCtrl" ng-controller='api_seg_ctrl_producao'>
			<div class="row">
				<div class="col-xs-12">
					<div class="card">

						<div class="card-header">
							<div class="card-title">
								<span class="title">MANUTENÇÂO DE SEGMENTO NEGÓCIO</span>
								<span class="description">acesso restrito</span>
							</div>
						</div>

						<div class="card-body no-padding">
							<div class="step card-no-padding">
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane fade in active" id="step1-2" aria-labelledby="home-tab">
									   <div class="row">
											<div class="col-sm-6">
												<table class="table table-hover">
													<thead>
														<tr>
															<th>Segmento de Negócio</th>
															<th>Ação</th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="segmentoNegocio in segmentoNegocio.list">
															<th scope="row">{{segmentoNegocio.descricao}}</th>
															<td>
																<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalAlterar" ng-click="updateSegmentoNegocio(segmentoNegocio.uid, segmentoNegocio.descricao)" style="margin-top: -7px; margin-bottom: -7px">editar</button>
																<button type="button" class="btn btn-danger btn-xs" ng-click="deleteSegmentoNegocio(segmentoNegocio.uid, segmentoNegocio.descricao)" style="margin-top: -7px; margin-bottom: -7px">excluir</button>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-offset-5">
												<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalIncluir" ng-click="novoSegmentoNegocio()" ng-hide="msgErro">novo</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

            <div id="resultErro" class="alert alert-danger alert-dismissible col-sm-6" role="alert" ng-show="msgErro" >
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Alerta! </strong>{{msgErro}}
            </div>

			<!-- Modal Alteração-->
			<div class="modal fade modal-info" id="modalAlterar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Alterar Segmento de Negócio</h4>
						</div>
						<div class="modal-body">
							<label for="segmentoNegocio">Segmento de Negócio</label>
							<input type="text" class="form-control" ng-model="idSegmento" ng-hide="true">
							<input type="text" class="form-control" placeholder="Segmento de Negócio" ng-model="descricaoSegmento">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
							<button type="button" class="btn btn-info" ng-click="saveSegmentoNegocio('A')">Salvar</button>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Modal Inclusão-->
			<div class="modal fade modal-info" id="modalIncluir" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Incluir Segmento de Negócio</h4>
						</div>
						<div class="modal-body">
							<label for="segmentoNegocio">Segmento de Negócio</label>
							<input type="text" class="form-control" placeholder="Segmento de Negócio" ng-model="descricaoSegmento">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
							<button type="button" class="btn btn-info" ng-click="saveSegmentoNegocio('I')">Salvar</button>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</body>
</html>
</asp:Content>