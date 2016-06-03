<%@ Page Language="C#" masterpagefile="../../_catalogs/masterpage/theme-api.master" title="2 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>

<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">
    <html>
	    <head>
</head>
<body>
            <div class="container" id="angularCtrl" ng-controller='api_ctrl'>                                    
                <div class="row content-container">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="divPrincipal" class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <span class="title">ATRIBUIÇÃO DE COORDENAÇÕES</span>
                                        <span class="description">acesso restrito</span>                                                                                   
                                    </div>
                                </div>

                                <div class="card-body no-padding">
                                    <div class="step card-no-padding">
                                        <%-- TABS HEAD --%>
                                        <ul class="nav nav-tabs nav-justified" role="tablist">
                                            <li role="step" class="active">
                                                <a href="#step1-2" id="step1-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">
                                                    <div class="icon fa fa-desktop"></div>
                                                    <div class="step-title">
                                                        <div class="title">COORDENAÇÃO DE T. I.</div>
                                                        <div class="description">{{coordenacaoTi.nome}} </div>
                                                        <div class="description" style="color:red" ng-hide="coordenacaoTi.nome"> Selecione uma Coodenação</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li role="step">
                                                <a href="#step2-2" role="tab" id="step2-tab" data-toggle="tab" aria-controls="profile">
                                                    <div class="icon fa fa-gears"></div>
                                                    <div class="step-title">
                                                        <div class="title">COORDENAÇÃO DE PROJ.</div>
                                                        <div class="description">{{coordenacaoProj.nome}}</div>
                                                        <div class="description" style="color:red" ng-hide='coordenacaoProj.nome'> Selecione uma Coodenação</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li role="step">
                                                <a href="#step3-2" role="tab" id="step3-tab" data-toggle="tab" aria-controls="profile">
                                                    <div class="icon fa fa-user"></div>
                                                    <div class="step-title">
                                                        <div class="title">GESTÃO DE ATIVOS</div>
                                                        <div class="description">Atribuições de Sistemas, Ferramentas e Disciplinas.</div>
                                                    </div>
                                                </a>
                                            </li>
                                        </ul>       
                                        <%-- TABS HEAD FIM--%>

                                
                                        <%-- TABS --%>
                                        <div class="tab-content">
                                            <%-- COORDENAÇÃO TI --%>
                                            <div role="tabpanel" class="tab-pane fade in active" id="step1-2" aria-labelledby="home-tab">
                                               <div class="row">
							                        <div id="divAreaCoordenacaoTI" class="col-md-3">
								                        <h4>Coordenações T.I. </h4>
							                        </div>
							                        <div id="divResponsavelTI" class="col-md-3" ng-show="coordenacaoTi.uid > 0">
								                        <h4>Responsável</h4>
							                        </div>			
							                        <div id="divBotaoTI" class="col-md-1" style="color:white">
								                    
							                        </div>		
							                        <div id="divSelecionaNovaCoordenacaoTI" class="col-md-4" ng-show="showListTi">
								                        <h4>Selecionar Novo Coordenador:</h4>
							                        </div>			
						                        </div>
						                
						                        <div class="row">                                            
							                            <div id="divCoordenaTi" class="col-md-3">
                                                            <%-- SELECT COORDENAÇÃO TI --%>
                                                            <select id="selCoordenacaoTi" 
                                                                class="form-control" 
                                                                ng-options="coordenacao as coordenacao.nome for coordenacao in coordenacoesTi.list | orderBy: 'nome'" 
                                                                ng-model="coordenacaoTi" 
                                                                ng-change="processaChangeCoordenadorTi();">
                                                                <option value="">Selecione</option> 
                                                            </select>
							                            </div>		  
							
							                            <div id="divCoordenaTiName" class="col-md-3" ng-show="coordenacaoTi.uid > 0">
                                                            <input class='form-control' id='NomeCoordenadorTI' type='text' 
                                                                ng-model="coordenadorTi.nome" placeholder='Não Indicado..' disabled />
							                            </div>
							
							                            <div id="divCoordenaTiBtn" class="col-md-1" ng-show="coordenacaoTi.uid > 0">
								                            <button type='button' id='CoordenaTiBtn' class='btn btn-info' style='margin-top:0px;' 
                                                                ng-click='pesquisarUsuariosLdap(1);showListTi = true'>Indicar</button>                                                            
							                            </div>
							                    
							                            <div id="divSelectAdCoordenacaoTI" class="col-md-4" ng-show="showListTi">
                                                            <select id='selFuncionariosCoordenacaoTI' 
                                                                multiple class='form-control' 
                                                                ng-options="funcionario as funcionario.name for funcionario in todosFuncionariosLdap | filter: {title: 'COORDENADOR'} | orderBy: 'name'" 
                                                                ng-model="funcionarioTi">
                                                            </select>
							                            </div>					
							
							                            <div id="SetaCoordenaTiDiv" class="col-md-1" ng-show="funcionarioTi">
								                            <button type='button' id='SetaCoordenaTiBtn' class='btn btn-info' style='margin-top:0px;' ng-click="trocarCoordenadorTi()">Trocar</button> 
							                            </div>
                                            
						                        </div>                                        

                                            </div>  <%-- FINAL TAB COORDENAÇÃO TI --%>

                                            <%-- TAB COORDENAÇÃO PROJETOS --%>
                                            <div role="tabpanel" class="tab-pane fade" id="step2-2" aria-labelledby="profile-tab">                                                

                                                <div class="row">
							                        <div id="divAreaCoordenacaoProj" class="col-md-3">
								                        <h4>Coordenações de Projeto</h4>
							                        </div>
							                        <div id="divResponsavelProj" class="col-md-3" style="display:none">
								                        <h4>Responsável</h4>
							                        </div>			
							                        <div id="divBotaoProj" class="col-md-1" style="color:white">
								                        <h4></h4>
							                        </div>		
							                        <div id="divSelecionaNovaCoordenacaoProj" class="col-md-4" style="display:none">
								                        <h4>Selecionar Novo Coordenador:</h4>
							                        </div>
						                        </div>	

						                        <div class="row">
                                                    <div id="divCoordenaProj" 	class="col-md-3">
                                                        <%-- SELECT COORDENAÇÃO PROJETOS --%>
                                                        <select id="selCoordenacaoProj" 
                                                                class="form-control"
                                                                ng-options="coordenacao as coordenacao.nome for coordenacao in coordenacoesProj.list | orderBy: 'nome'"                                                                                                                                  
                                                                ng-model="coordenacaoProj"
                                                                ng-change="processaChangeCoordenadorProj();">

                                                            <option value="">Selecione</option> 

                                                        </select>
                                                    </div>
                                            
							                        <div id="divCoordenaProjName" class="col-md-3" ng-show="coordenacaoProj.uid > 0">
                                                        <input class='form-control' ng-model="coordenadorProj.nome" id='NomeCoordenadorProj' type='text' placeholder='Não Indicado..' disabled />
							                        </div>
							
							                        <div id="divCoordenaProjBtn" class="col-md-1" ng-show="coordenacaoProj.uid > 0">								                    
								                        <button type='button' id='CoordenaProjBtn' class='btn btn-info' style='margin-top:0px' 
                                                            ng-click='pesquisarUsuariosLdap(2); showListProj = true'>Indicar</button>
							                        </div>
				
							                        <div id="divSelectAdCoordenacaoProj" class="col-md-4" ng-show="showListProj">                                                    
                                                        <select id='selFuncionariosCoordenacaoProj' 
                                                                multiple class='form-control' 
                                                                ng-options="funcionario as funcionario.name for funcionario in todosFuncionariosLdap | filter: {title: 'PROJETO'} | orderBy: 'name'"                                                                
                                                                ng-model="funcionarioProj">
                                                        </select>
							                        </div>
							
							                        <div id="SetaCoordenaProjDiv" class="col-md-1" ng-show="funcionarioProj">
								                        <button type='button' id='SetaCoordenaProjBtn' class='btn btn-info' style='margin-top:0px' ng-click="trocarCoordenadorProj()">Trocar</button>                                                     
							                        </div>
						                        </div>


                                            </div>      <%-- FINAL TAB COORDENAÇÃO PROJETOS --%>

                                            <%-- TAB GESTÃO --%>                                            
                                            <div role="tabpanel" class="tab-pane fade" id="step3-2" aria-labelledby="dropdown1-tab">
                                                <div class="col-md-12">
                                                	<!-- Inicio da Div Tabs Gestão-->
						                            <div id="tabsAtivos" >
					        
					                                    <ul class="nav nav-pills" id="tabHrefAtivos" style="background-color: #EAEAEA">
					                                        <li style="background-color: #EAEAEA"><a id="tab1" data-toggle="tab" href="#sectionFuncionarios">Funcionários</a></li>            
					                                        <li style="background-color: #EAEAEA"><a id="tab2" data-toggle="tab" href="#sectionSistemas">Sistemas</a></li>
                                                            <li style="background-color: #EAEAEA"><a id="tab3" data-toggle="tab" href="#sectionAtivos">Ativos</a></li>
					                                    </ul>
					        
					                                    <div class="tab-content">					        	
					                                        <div id="sectionFuncionarios" class="tab-pane">
					            	                            <br />
					            	                            <div class="row">
					            	 	                            <div id="divOrganizaTableAtivos" class="col-md-8">
					            	 		                            <div class="panel panel-default">
					            	 			                            <div class="panel-heading"><strong>Funcionários da Coordenação</strong></div>

                                                                             <table class="datatable table table-striped" cellspacing="0" width="100%" id="tableFuncionariosCoordenacao">
                                                                                <thead>
                                                                                    <tr>                                                                                        
                                                                                        <th>Matrícula</th>
                                                                                        <th>Nome</th>
                                                                                        <th>Cargo</th>
                                                                                        <th>Ação</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr ng-repeat="funcionario in funcionariosCoordenacao">                                                                                        
                                                                                        <th scope="row">{{funcionario.matricula}}</th>
                                                                                        <td>{{funcionario.nome}}</td>
                                                                                        <td>{{funcionario.cargo}}</td>
                                                                                        <td>
                                                                                            <button type='button' id="{{'btnFuncionario' + funcionario.uid}}" class='btn btn-danger btn-xs' 
                                                                                                ng-click="excluirFuncionarioCoordenacao($index)" style="margin-bottom: -7px;margin-top: -7px">Excluir
                                                                                            </button>
                                                                                        </td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>

					            			                            </div>	
					            		                            </div>
					            		
					            		                            <div id="incluiFuncionarioCoordenacaoDiv" class="col-md-1" ng-show="coordenacaoProj">
                                                                         <button type="button" class="btn btn-info" ng-click="pesquisarUsuariosLdap(3)">Incluir</button>                                                                        
					            		                            </div>  

					            	                            </div>			
					                                        </div>
					            
					                                        <div id="sectionSistemas" class="tab-pane fase">
					            	                            <br /> 	
					            	                            <div class="row">
					            		
					            		                            <div id="divOrganizaTableSitemas" class="col-md-7">
						            		                            <div class="panel panel-default">
							            		                            <div class="panel-heading"><strong>Sistemas da Coordenação</strong></div>
								            	                            
                                                                            <table class="datatable table table-striped" cellspacing="0" width="100%" id="tableSistemasCoordenacao">					            				                            
	                                                                            <thead>
		                                                                            <tr>                                                                                        
			                                                                            <th>Sistema</th>
			                                                                            <th>Descrição</th>
			                                                                            <th>Ação</th>                                                                                            
		                                                                            </tr>
	                                                                            </thead>
	                                                                            <tbody>
		                                                                            <tr ng-repeat="sistema in sistemasCoordenacao">                                                                                        
			                                                                            <th scope="row">{{sistema.sistema}}</th>
			                                                                            <td>{{sistema.descricao}}</td>                                                                                            
			                                                                            <td>
				                                                                            <button type='button' id="{{sistemasCoordenacao.codigo}}" class='btn btn-sm' 
					                                                                            ng-click="mostrarFuncionarioSistema($index)" style="margin-bottom: -7px;margin-top: -7px">Responsáveis
				                                                                            </button>
			                                                                            </td>
		                                                                            </tr>
	                                                                            </tbody>
                                                                            </table>
							            	                            </div>
						            	                            </div>
						            	
						            	                            <div id="divOrganizaTableFuncionariosResponsaveisSistemas" class="col-md-5">
						            		                            <div class="panel panel-default">
						            			                            <div class="panel-heading">
													                            <strong>Funcionários Responsáveis - {{sistemaSelecionado.sistema}} </strong> </div>
						            			                            <div id="divFuncionariosResponsaveisSitemas">						            			                                                                                                                
                                                                                <table class="datatable table table-striped" cellspacing="0" width="100%" id="tableFuncionariosSistemaSelecionado">					            				                            
	                                                                                <thead>
		                                                                                <tr>                                                                                        
			                                                                                <th>Matrícula</th>
			                                                                                <th>Nome</th>
			                                                                                <th>Ação</th>			                                                                                
		                                                                                </tr>
	                                                                                </thead>
	                                                                                <tbody>
		                                                                                <tr ng-repeat="funcionarioSistemaSelecionado in funcionariosSistemaSelecionado">                                                                                        
			                                                                                <th scope="row">{{funcionarioSistemaSelecionado.matricula}}</th>
			                                                                                <td>{{funcionarioSistemaSelecionado.nome}}</td>			                                                                                
			                                                                                <td>
				                                                                                <button type='button' id="{{'btnFuncionarioSistemaSelecionado' + funcionarioSistemaSelecionado.uid}}" 
                                                                                                    class='btn btn-danger btn-xs' 
					                                                                                ng-click="excluirFuncionarioSistema($index)" style="margin-bottom: -7px;margin-top: -7px">Excluir
				                                                                                </button>
			                                                                                </td>
		                                                                                </tr>
	                                                                                </tbody>
                                                                                </table>                                                                              

                                                                            </div>	
						            		                            </div>		
						            	                            </div>
						            	
					            	                            </div>
					            	                            <div class="row">
					            		                            <div class="col-md-7"></div>
					            		                            <div class="col-md-1">
                                                                        <button type='button' id='incluiFuncionarioSistemaBtn' ng-show="sistemaSelecionado"
                                                                            class='btn btn-primary' data-toggle='modal' ng-click="carregarFuncionariosCoordenacaoSistema()" >Incluir</button>
										                            </div>	            		
					            	                            </div>
					                                        </div>		            

                                                            <div id="sectionAtivos" class="tab-pane fase">
					            	                            <br /> 	
					            	                            <div class="row">
					            		
					            		                            <div id="divOrganizaTableFerramentas" class="col-md-7">
						            		                            <div class="panel panel-default">
							            		                            <div class="panel-heading">
												                            <strong>Ativos da Coordenação</strong></div>
								            	                            <div id="divFerramentasFuncionarios" >                                                     
                                                                                <table class="datatable table table-striped" cellspacing="0" width="100%" id="tableAtivosCoordenacao">					            				                            
	                                                                                <thead>
		                                                                                <tr>                                                                                        
			                                                                                <th>Nome</th>
			                                                                                <th>Descricao</th>
			                                                                                <th>Tipo</th>			
                                                                                            <th>Ação</th>
		                                                                                </tr>
	                                                                                </thead>

	                                                                                <tbody>
		                                                                                <tr ng-repeat="ativo in ativosCoordenacao">
			                                                                                <th scope="row">{{ativo.nome}}</th>
			                                                                                <td>{{ativo.descricao}}</td>			
                                                                                            <td>{{ativo.tipoAtivo}}</td>			
			                                                                                <td>
				                                                                                <button type='button' id="{{ativo.uid}}" class='btn btn-sm' 
					                                                                                ng-click="mostrarFuncionarioAtivo($index)" style="margin-bottom: -7px;margin-top: -7px">Responsáveis
				                                                                                </button>
			                                                                                </td>
		                                                                                </tr>
	                                                                                </tbody>
                                                                                </table>
								            	                            </div>
							            	                            </div>
						            	                            </div>
						            	
						            	                            <div id="divOrganizaTableFuncionariosResponsaveisFerramentas" class="col-md-5">
						            		                            <div class="panel panel-default">
						            			                            <div class="panel-heading"><strong>Funcionários Responsáveis - {{ativoSelecionado.nome}} </strong> </div>
						            			                            <div id="divFuncionariosResponsaveisAtivos">	
						            			                                <table id="tableFuncionariosFerramentas" ></table>
                                                                                
                                                                                <table class="datatable table table-striped" cellspacing="0" width="100%" id="tableFuncionariosAtivoSelecionado">					            				                            
	                                                                                <thead>
		                                                                                <tr>                                                                                        
			                                                                                <th>Matrícula</th>
			                                                                                <th>Nome</th>
			                                                                                <th>Ação</th>			                                                                                
		                                                                                </tr>
	                                                                                </thead>
	                                                                                <tbody>
		                                                                                <tr ng-repeat="funcionarioAtivoSelecionado in funcionariosAtivoSelecionado">                                                                                        
			                                                                                <th scope="row">{{funcionarioAtivoSelecionado.matricula}}</th>
			                                                                                <td>{{funcionarioAtivoSelecionado.nome}}</td>			                                                                                
			                                                                                <td>
				                                                                                <button type='button' id="{{'btnfuncionarioAtivoSelecionado' + funcionarioAtivoSelecionado.uid}}" 
                                                                                                    class='btn btn-danger btn-xs' 
					                                                                                ng-click="excluirFuncionarioAtivo($index)" style="margin-bottom: -7px;margin-top: -7px">Excluir
				                                                                                </button>
			                                                                                </td>
		                                                                                </tr>
	                                                                                </tbody>
                                                                                </table>                                                                              			

                                                                            </div>
						            		                            </div>		
						            	                            </div>
                                                                </div>					            	                            
                                                                <div class="row">
					            		                            <div class="col-md-7"></div>
					            		                            <div class="col-md-1">
                                                                        <button type='button' id='incluiFuncionarioAtivoBtn' ng-show="ativoSelecionado"
                                                                            class='btn btn-primary' data-toggle='modal' ng-click="carregarFuncionariosCoordenacaoAtivo()">Incluir</button>
										                            </div>	            		
					            	                            </div>
					            	                            
					                                        </div>
					                                    </div>
					                                </div> <!-- Final da Div Tabs Gestão -->	
                                                
                                                </div>
                                            </div>
                                        </div>      <%-- TABS FIM --%>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal Funcionario_Unidades-->
			    <div id="inclusaoFuncionarioAdModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				    <div class="modal-dialog modal-lg" role="document">
				        <!-- Modal content-->
				        <div class="modal-content">
				            <div class="modal-header">				            
				                <h4 class="modal-title" id="myModalLabel">Atribuir Funcionário a Coordenação: {{coordenacaoProj.nome}} </h4>
				            </div>
				
				            <div class="modal-body">
				                <div class="panel panel-default">
				                    <div class="panel-heading">
				                        Funcionários da CEDESSP
				                    </div>
				                    <div id="divInclusaoFuncionariosCoordenacaoProjetos" style='padding:5px'>
                                        <table id='tableAdUsuarios' class='table table-striped table-hover'>
                                            <thead>
						                        <tr>
						                         <th>Matricula</th>
						                          <th>Nome</th>
						                          <th>Cargo</th>
						                          <th>Ação</th>
						                        </tr>
				      		                </thead>
                                            <tbody>
                                                <%--<tr ng-repeat="funcionarioLdap in todosFuncionariosLdap | filter: {sAMAccountName: 'c'}">--%>                                                                                                        
                                                <tr ng-repeat="funcionarioLdap in todosFuncionariosLdap">
                                                    <td>{{funcionarioLdap.sAMAccountName}}</td>
                                                    <td>{{funcionarioLdap.name}}</td>
                                                    <td>{{funcionarioLdap.title}}</td>
                                                    <td>
                                                        <button id="{{'bntFuncionarioLdap' +  funcionarioLdap.sAMAccountName}}" type='button' class='btn btn-info btn-xs' 
                                                            style="margin-bottom: -7px;margin-top: -7px" 
                                                            ng-click="incluirFuncionarioCoordenacao($index)">Incluir</button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
				                    </div>
				                </div>
				            </div>
				
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">
				                    Fechar
				                </button>                                        
				            </div>
				
				        </div>
				    </div>
			    </div> <!-- Final da Div Modal -->

                <!-- Modal Funcionario Sistemas-->
				<div id="inclusaoFuncionarioSistemasModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				        <div class="modal-dialog" role="document">
				
				        <!-- Modal content-->
				        <div class="modal-content">
				            <div class="modal-header">				                     	                   
				                <h4 class="modal-title" id="myModalLabel">Atribuir Funcionário ao sistema: {{sistemaSelecionado.sistema}}</h4>
				            </div>
				                    
				            <div class="modal-body">
				                <div class="panel panel-default">
						            <div class="panel-heading">
										Funcionários da Coordenação: {{coordenacaoProj.nome}} </div>
					                <div id="divInclusaoFuncionariosSistemas">
					                	
                                        <table class="datatable table table-striped" cellspacing="0" width="100%" id="tablefuncionariosCoordenacaoSistemas">					            				                            
	                                        <thead>
		                                        <tr>                                                                                        
			                                        <th>Matrícula</th>
			                                        <th>Nome</th>
			                                        <th>Ação</th>
			                                        <th>Cargo</th>
		                                        </tr>
	                                        </thead>
	                                        <tbody>                                    
		                                        <tr ng-repeat="funcionariosSistemas in funcionariosCoordenacaoSistemas">                                                                                        
			                                        <th scope="row">{{funcionariosSistemas.matricula}}</th>
			                                        <td>{{funcionariosSistemas.nome}}</td>
			                                        <td>{{funcionariosSistemas.cargo}}</td>
			                                        <td>
				                                        <button type='button' id="{{'bntFuncionarioSistema' + funcionariosSistemas.uid}}" class='btn btn-info btn-xs' 
					                                        ng-click="incluirFuncionarioSistema($index)" style="margin-bottom: -7px;margin-top: -7px">Incluir
				                                        </button>
			                                        </td>
		                                        </tr>
	                                        </tbody>
                                        </table>
                                    </div>
					            </div>
				            </div>
				                    
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>                                        
				            </div>
				                    
				        </div>	
				    </div>
				</div> <!-- Final da Div Modal -->

                <!-- Modal Funcionario Ativos-->
				<div id="inclusaoFuncionarioAtivosModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				        <div class="modal-dialog" role="document">
				
				        <!-- Modal content-->
				        <div class="modal-content">
				            <div class="modal-header">				                     	                   
				                <h4 class="modal-title" id="myModalLabel">Atribuir Funcionário ao Ativo: {{ativoSelecionado.nome}}</h4>
				            </div>
				                    
				            <div class="modal-body">
				                <div class="panel panel-default">
						            <div class="panel-heading">
										Funcionários da Coordenação: {{coordenacaoProj.nome}} </div>
					                <div id="divInclusaoFuncionariosSistemas">
					                	
                                        <table class="datatable table table-striped" cellspacing="0" width="100%" id="tablefuncionariosCoordenacaoAtivos">
	                                        <thead>
		                                        <tr>                                                                                        
			                                        <th>Matrícula</th>
			                                        <th>Nome</th>
			                                        <th>Ação</th>
			                                        <th>Cargo</th>
		                                        </tr>
	                                        </thead>
	                                        <tbody>                                    
		                                        <tr ng-repeat="funcionarioAtivos in funcionariosCoordenacaoAtivos">                                                                                        
			                                        <th scope="row">{{funcionarioAtivos.matricula}}</th>
			                                        <td>{{funcionarioAtivos.nome}}</td>
			                                        <td>{{funcionarioAtivos.cargo}}</td>
			                                        <td>
				                                        <button type='button' id="{{'bntFuncionarioAtivos' + funcionarioAtivos.uid}}" class='btn btn-info btn-xs' 
					                                        ng-click="incluirFuncionarioAtivo($index)" style="margin-bottom: -7px;margin-top: -7px">Incluir
				                                        </button>
			                                        </td>
		                                        </tr>
	                                        </tbody>
                                        </table>
                                    </div>
					            </div>
				            </div>
				                    
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>                                        
				            </div>
				                    
				        </div>	
				    </div>
				</div> <!-- Final da Div Modal -->

                <div id="resultErro" class="alert alert-danger alert-dismissible col-sm-6" role="alert" ng-show="msgErro" >
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Alerta! </strong>{{msgErro}} 
                </div>

                <div id="resultInfo" class="alert alert-info alert-dismissible col-sm-6" role="alert" ng-show="msgInfo" >
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Atenção! </strong>{{msgInfo}} <span id="msgComplemento"></span>
                </div>

                <div id="result" class="alert fresh-color alert-success col-sm-6" role="alert" style="display:none">
                    <strong>OK! </strong> {{msgSucesso}}
                </div>

            </div>            

        </body>
    </html>    
</asp:Content>