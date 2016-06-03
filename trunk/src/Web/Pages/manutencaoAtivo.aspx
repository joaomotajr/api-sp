<%@ Page Language="C#" masterpagefile="../../../../_catalogs/masterpage/theme-api.master" title="2 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>
<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">
<html>
	<head>
<meta name="WebPartPageExpansion" content="full" />
<style>
			.error-message
			{
				padding-top: 3px;
				padding-bottom: 3px;
				font-size: 11px;
				margin-bottom: 0px;
			}
		</style>
	</head>
	<body>
		<div id="angularCtrl" ng-controller='api_ativo_producao'>
			<div class="row">
				<div class="col-xs-12">
					<div class="card">
						<div class="card-header">
							<div class="card-title">
								<span class="title">MANUTENÇÃO ATIVOS</span>
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
															<th>Nome</th>
															<th>Descrição</th>
															<th>Tipo</th>															
                                                            <th>Coordenação</th>
                                                            
                                                            <th>Ação</th>
														</tr>
													</thead>
													<tbody>
                                                        
														<tr ng-repeat="ativo in ativoAll.list | filter: {TI: filtro}" ng-show="filtro" >
															<td scope="row">{{ativo.nome}}</td>
															<td scope="row">{{ativo.descricao}}</td>
															<td scope="row">{{ativo.tipoAtivo}}</td>                                                            
                                                            <td scope="row">{{ativo.coordenacaoDto.nome}}</td>                                                            
															<td>
																<button type="button" class="btn btn-danger btn-xs" ng-click="deleteAtivo(ativo.uid)" style="margin-top: -7px; margin-bottom: -7px">excluir</button>
															</td>
														</tr>

                                                        <tr ng-repeat="ativo in ativoAll.list" ng-hide="filtro" >
															<td scope="row">{{ativo.nome}}</td>
															<td scope="row">{{ativo.descricao}}</td>
															<td scope="row">{{ativo.tipoAtivo}}</td>                                                            
                                                            <td scope="row">{{ativo.coordenacaoDto.nome}}</td>                                                            
															<td>
																<button type="button" class="btn btn-danger btn-xs" ng-click="deleteAtivo(ativo.uid)" style="margin-top: -7px; margin-bottom: -7px">excluir</button>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-offset-5">
												<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modalIncluir" ng-click="novoAtivo()" ng-hide="msgErro">novo</button>
											</div>
										</div>

                                        <div id="resultErro" class="alert alert-danger alert-dismissible col-sm-6" role="alert" ng-show="msgErro" >
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <strong>Alerta! </strong>{{msgErro}}
                                        </div>
										
										<!-- Modal Inclusão-->
										<div class="modal fade modal-info" id="modalIncluir" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														<h4 class="modal-title" id="myModalLabel">Incluir Ativo</h4>
													</div>
													<div class="modal-body">
														<label for="segmentoNegocio">Nome</label>
														<input type="text" class="form-control" placeholder="Nome" ng-model="nome">
														<div class="alert alert-danger error-message" role="alert" ng-show="erroNome">
															<strong>Alerta!&nbsp;</strong>{{msgErrorNome}}
														</div>
														<br>																					
														<label for="segmentoNegocio">Descrição</label>
														<input type="text" class="form-control" placeholder="Descrição" ng-model="descricao">
														<div class="alert alert-danger error-message" role="alert" ng-show="erroDescricao">
															<strong>Alerta!&nbsp;</strong>{{msgErrorDescricao}}
														</div>
														<br>
														<label for="segmentoNegocio">Tipo</label>
														<select class="form-control" ng-model="selTipoAtivo">
															<option value="">Selecione</option> 
															<option value="0">Disciplina</option> 
															<option value="1">Ferramentas</option> 
														</select>
														<div class="alert alert-danger error-message" role="alert" ng-show="erroTipoAtivo">
															<strong>Alerta!&nbsp;</strong>{{msgErrorTipoAtivo}}
														</div>
														<br>
														<label for="coordenacaoTi">Coordenações TI</label>
														<select id="coordenacaoTi" 
															class="form-control" 
															ng-options="coordenacao as coordenacao.nome for coordenacao in coordenacoesTi.list | orderBy: 'nome'" 
															ng-model="coordenacaoTi" 
															ng-change="processaChangeCoordenadorTi();">
															<option value="">Selecione</option> 
														</select>
														<div class="alert alert-danger error-message" role="alert" ng-show="erroCoordenacaoTI">
															<strong>Alerta!&nbsp;</strong>{{msgErrorCoordenacaoTI}}
														</div>
														<br>
														<label for="coordenacaoProjeto">Coordenações Projeto</label>
														<select id="selCoordenacaoProj" 
                                                                class="form-control"
                                                                ng-options="coordenacao as coordenacao.nome for coordenacao in coordenacoesProj.list | orderBy: 'nome'"                                                                                                                                  
                                                                ng-model="coordenacaoProj">
                                                            <option value="">Selecione</option> 
                                                        </select>
														<div class="alert alert-danger error-message" role="alert" ng-show="erroCoordenacaoProjeto">
															<strong>Alerta!&nbsp;</strong>{{msgErrorCoordenacaoProjeto}}
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
														<button type="button" class="btn btn-info" ng-click="saveAtivo('I')">Salvar</button>
													</div>
												</div>
											</div>
										</div>
										
										<!-- Modal Alteração-->
										<div class="modal fade modal-info" id="modalAlterar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														<h4 class="modal-title" id="myModalLabel">Alterar Ativo</h4>
													</div>
													<div class="modal-body">
														<label for="segmentoNegocio">Nome</label>
														<input type="text" class="form-control" placeholder="Nome" ng-model="nome">
														<div class="alert alert-danger error-message" role="alert" ng-show="erroNome">
															<strong>Alerta!&nbsp;</strong>{{msgErrorNome}}
														</div>
														<br>																					
														<label for="segmentoNegocio">Descrição</label>
														<input type="text" class="form-control" placeholder="Descrição" ng-model="descricao">
														<div class="alert alert-danger error-message" role="alert" ng-show="erroDescricao">
															<strong>Alerta!&nbsp;</strong>{{msgErrorDescricao}}
														</div>
														<br>
														<label for="segmentoNegocio">Tipo</label>
														<select class="form-control" ng-model="selTipoAtivo">
															<option value="">Selecione</option> 
															<option value="0">DISCIPLINA</option> 
															<option value="1">FERRAMENTAS</option> 
														</select>
														<div class="alert alert-danger error-message" role="alert" ng-show="erroTipoAtivo">
															<strong>Alerta!&nbsp;</strong>{{msgErrorTipoAtivo}}
														</div>
														<br>
														<label for="coordenacaoTi">Coordenações TI</label>
														<select id="coordenacaoTi" 
															class="form-control" 
															ng-options="coordenacao as coordenacao.nome for coordenacao in coordenacoesTi.list | orderBy: 'nome' track by coordenacaoTi.nome" 
															ng-model="coordenacaoTi" 
															ng-change="processaChangeCoordenadorTi();">
															<option value="">Selecione</option> 
														</select>
														<div class="alert alert-danger error-message" role="alert" ng-show="erroCoordenacaoTI">
															<strong>Alerta!&nbsp;</strong>{{msgErrorCoordenacaoTI}}
														</div>
														<br>
														<label for="coordenacaoProjeto">Coordenações Projeto</label>
														<select id="coordenacaoProj" 
                                                                class="form-control"
                                                                ng-options="coordenacao as coordenacao.nome for coordenacao in coordenacoesProj.list | orderBy: 'nome' track by coordenacaoProj.nome"
                                                                ng-model="coordenacaoProj">
                                                            <option value="">Selecione</option> 
                                                        </select>
														<div class="alert alert-danger error-message" role="alert" ng-show="erroCoordenacaoProjeto">
															<strong>Alerta!&nbsp;</strong>{{msgErrorCoordenacaoProjeto}}
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
														<button type="button" class="btn btn-info" ng-click="saveAtivo('A')">Salvar</button>
													</div>
												</div>
											</div>
										</div>
										
									</div>
								</div>
							</div>	
						</div>
					</div>
				</div>	
			</div>
		</div>
	</body>
</html>
</asp:Content>