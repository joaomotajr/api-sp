<%@ Page Language="C#" masterpagefile="../../../../_catalogs/masterpage/theme-api.master" title="2 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>
<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">
    <meta http-equiv="Content-Type" content="text/html; charset=8859-1" />
    	<head>
<meta name="WebPartPageExpansion" content="full" />

        <link rel="stylesheet" href="../../../StyleLibrary/Index/style.css" />
        <link rel="stylesheet" href="../../../StyleLibrary/Index/styleMenu.css" /> 

        <style type="text/css">
            
			.gray{
                background-color: #808080;
				color: #FFF;
            }
			.card.gray:hover {
				background-color: #454545;
			}
			
			.purple{
                background-color: #800080;
				color: #FFF;
            }
			.card.purple:hover {
				background-color: #450045;
			}
			
			.pink{
                background-color: #ff69b4;
				color: #FFF;
            }
			.card.pink:hover {
				background-color: #55002b;
			}
			
			.navy{
                background-color: #000080;
				color: #FFF;
            }
			.card.navy:hover {
				background-color: #000045;
			}
			
			.dark-green{
                background-color: #006400;
				color: #FFF;
            }
			.card.dark-green:hover {
				background-color: #002900;
			}

        </style>
	    </head>
			
			<div class="conteudo" id="angularCtrl" ng-controller='index_ctrl_producao'>
                <div class="panel panel-danger" ng-show="msgErro">
                    <div class="panel-body">
                        {{msgErro}}
                    </div>
                </div>
				<div class="row">
					<main class="cd-main-content">
						<div class="cd-tab-filter-wrapper">
							<div class="cd-tab-filter">
								<ul class="cd-filters">
									<li class="placeholder"> 
										<a data-type="all" href="#">TODAS</a> <!-- selected option on mobile -->
									</li>
									<li class="filter"><a class="selected" href="#0" data-type="all">CEDESSP</a></li>
									<li class="filter" data-filter=".suporte"><a href="#0" data-type="Suporte"><i class="icon fa fa-keyboard-o fa-1.5x"></i> Suporte</a></li>
									<li class="filter" data-filter=".infraestrutura"><a href="#0" data-type="Infraestrutura"><i class="icon fa fa-server fa-1.5x"></i> Infraestrutura</a></li>
									<li class="filter" data-filter=".sistemasfinanceiros"><a href="#0" data-type="SistemasFinanceiros"><i class="icon fa fa-money fa-1.5x"></i> SISTEMAS FINANCEIROS</a></li>
									<li class="filter" data-filter=".captacaojudicial"><a href="#0" data-type="Captacaojudicial"><i class="icon fa fa-book fa-1.5x"></i> CAPTAÇÃO / JUDICIAL</a></li>
								</ul> <!-- cd-filters -->
							</div> <!-- cd-tab-filter -->
						</div> <!-- cd-tab-filter-wrapper -->

						<div class="cd-tab-filter-wrapper">
							<div class="cd-tab-filter">
								<ul class="cd-filters">
									<li class="placeholder">
										<a data-type="all" href="#">TODAS</a> <!-- selected option on mobile -->
									</li>                    
									<li class="filter" data-filter=".cartoesemprestarfinanciar"><a href="#0" data-type="CartoesEmprestarFinanciar"><i class="icon fa fa-credit-card fa-1.5x"></i> CARTÕES</a></li>    
									<li class="filter" data-filter=".administrativobackoffice"><a href="#0" data-type="AdministrativoBackffice"><i class="icon fa fa-paperclip fa-1.5x"></i> ADM e BACKOFFICE</a></li>
									<li class="filter" data-filter=".servicosbancarios"><a href="#0" data-type="ServicosBancarios"><i class="icon fa fa-bank fa-1.5x"></i> Serviços Bancários</a></li> 
									<li class="filter" data-filter=".canais"><a href="#0" data-type="Canais"><i class="icon fa fa-laptop fa-1.8x"></i> CANAIS</a></li>
									<li class="filter" data-filter=".mobilidade"><a href="#0" data-type="Mobilidade"><i class="icon fa fa-mobile fa-1.5x"></i> MOBILIDADE</a></li>                                
						
								</ul> <!-- cd-filters -->
							</div> <!-- cd-tab-filter -->
						</div> <!-- cd-tab-filter-wrapper -->
						
						<div class="col-md-4"></div>
						<div id="searchInCoordenacoes" class="cd-filter-content col-md-4" style="margin-top: 5px; margin-bottom: -95px">
							<input type="search" class="form-control" placeholder="Pesquisar">
                            {{todosSistemas}}
						</div>
						<div class="col-md-4"></div>
						 <%--<div class="panel panel-danger" ng-show ="msgErro">
                            <div class="panel-body">
                                {{msgErro}}
                            </div>
                        </div> --%>
						<section class="cd-gallery">
							<ul>
								<li id="CEDESSP010"  class="mix suporte ESCRITORIO PROJETOS">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Escritório de Projetos">
										<div class="card blue summary-inline">
											<div class="card-body">
												<i class="icon fa fa-keyboard-o fa-3x"></i>
												<div class="content">													
													<div>CEDESSP010</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP020"  class="mix suporte CAPACIDADE PRODUTIVA">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Capacidade Produtiva">
									<div class="card blue summary-inline">
										<div class="card-body">
											<i class="icon fa fa-keyboard-o fa-3x"></i>
											<div class="content">
												<div>CEDESSP020</div>
											</div>
											<div class="clear-both"></div>
										</div>
									</div>
								</a>
								</li>
								<li id="CEDESSP040" class="mix suporte SUSTENTAÇÃO TECNOLOGIA">
									<a href="http://unidades/sites/CEDESSP/EQUIPES/SitePages{{uri}}cedessp040_dashboard.aspx"  data-toggle="tooltip" data-placement="top" title="Sustentação a Tecnologia">
										<div class="card blue summary-inline">
											<div class="card-body">
												<i class="icon fa fa-keyboard-o fa-3x"></i>
												<div class="content">
													<div>CEDESSP040</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>                            
								<li id="CEDESSP050" class="mix suporte qualidade">
									<a href="http://unidades/sites/CEDESSP/EQUIPES/SitePages{{uri}}cedessp050_dashboard.aspx" data-toggle="tooltip" data-placement="top" title="Qualidade">
										<div class="card blue summary-inline">
											<div class="card-body">
												<i class="icon fa fa-keyboard-o fa-3x"></i>
												<div class="content">
													<div>CEDESSP050</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>

								<li id="CEDESSP240" class="mix infraestrutura">
									<a href="http://unidades/sites/CEDESSP/EQUIPES/SitePages{{uri}}cedessp240_dashboard.aspx" data-toggle="tooltip" data-placement="top" title="Infraestrutura I">
										<div class="card yellow summary-inline">
											<div class="card-body">
												<i class="icon fa fa-server fa-3x"></i>
												<div class="content">
													<div>CEDESSP240</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP250" class="mix infraestrutura">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Infraestrutura II">
										<div class="card yellow summary-inline">
											<div class="card-body">
												<i class="icon fa fa-server fa-3x"></i>
												<div class="content">
													<div>CEDESSP250</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP330" class="mix sistemasfinanceiros SISTEMAS FINANCEIROS">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Sistemas Financeiros I">
										<div class="card green summary-inline">
											<div class="card-body">
												<i class="icon fa fa-money fa-3x"></i>
												<div class="content">
													<div>CEDESSP330</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP340" class="mix sistemasfinanceiros SISTEMAS FINANCEIROS" data-toggle="tooltip" data-placement="top" title="Sistemas Financeiros II">
									<a href="#">
										<div class="card green summary-inline">
											<div class="card-body">
												<i class="icon fa fa-money fa-3x"></i>
												<div class="content">
													<div>CEDESSP340</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP380" class="mix captacaojudicial CAPTACAO JUDICIAL">
									<a href="http://unidades/sites/CEDESSP/EQUIPES/SitePages{{uri}}cedessp380_dashboard.aspx" data-toggle="tooltip" data-placement="top" title="Captação Judicial">
										<div class="card gray summary-inline">
											<div class="card-body">
												<i class="icon fa fa-book fa-3x"></i>
												<div class="content">
													<div>CEDESSP380</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP410" class="mix cartoesemprestarfinanciar CARTOES EMPRESTAR FINANCIAR">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Cartões, Financiar e Emprestar">
										<div class="card purple summary-inline">
											<div class="card-body">
												<i class="icon fa fa-credit-card fa-3x"></i>
												<div class="content">
													<div>CEDESSP410</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>            
								<li id="CEDESSP550" class="mix administrativobackoffice ADMINISTRATIVO BACKOFFICE">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Administrativo e Backoffice">
										<div class="card red summary-inline">
											<div class="card-body">
												<i class="icon fa fa-paperclip fa-3x"></i>
												<div class="content">
													<div>CEDESSP550</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP570" class="mix servicosbancarios SERVICOS BANCARIOS">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Serviços Bancários I">
										<div class="card pink summary-inline">
											<div class="card-body">
												<i class="icon fa fa-bank fa-3x"></i>
												<div class="content">
													<div>CEDESSP570</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP580" class="mix servicosbancarios SERVICOS BANCARIOS">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Serviços Bancários II">
										<div class="card pink summary-inline">
											<div class="card-body">
												<i class="icon fa fa-bank fa-3x"></i>
												<div class="content">
													<div>CEDESSP580</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP590" class="mix servicosbancarios SERVICOS BANCARIOS">
									<a href="http://unidades/sites/CEDESSP/EQUIPES/SitePages{{uri}}cedessp590_dashboard.aspx" data-toggle="tooltip" data-placement="top" title="Serviços Bancários III">
										<div class="card pink summary-inline">
											<div class="card-body">
												<i class="icon fa fa-bank fa-3x"></i>
												<div class="content">
													<div>CEDESSP590</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP610" class="mix canais">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Canais I">
										<div class="card navy summary-inline">
											<div class="card-body">
												<i class="icon fa fa-laptop fa-3x"></i>
												<div class="content">
													<div>CEDESSP610</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>                
								<li id="CEDESSP620" class="mix canais">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Canais II">
										<div class="card navy summary-inline">
											<div class="card-body">
												<i class="icon fa fa-laptop fa-3x"></i>
												<div class="content">
													<div>CEDESSP620</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP630" class="mix canais">
									<a href="http://unidades/sites/CEDESSP/EQUIPES/SitePages{{uri}}cedessp630_dashboard.aspx" data-toggle="tooltip" data-placement="top" title="Canais III">
										<div class="card navy summary-inline">
											<div class="card-body">
												<i class="icon fa fa-laptop fa-3x"></i>
												<div class="content">
													<div>CEDESSP630</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP640" class="mix canais">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Canais VI">
										<div class="card navy summary-inline">
											<div class="card-body">
												<i class="icon fa fa-laptop fa-3x"></i>
												<div class="content">
													<div>CEDESSP640</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>
								<li id="CEDESSP650" class="mix canais">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Canais V">
										<div class="card navy summary-inline">
											<div class="card-body">
												<i class="icon fa fa-laptop fa-3x"></i>
												<div class="content">
													<div>CEDESSP650</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>                            
								<li id="CEDESSP660" class="mix mobilidade">
									<a href="#" data-toggle="tooltip" data-placement="top" title="Mobilidade">
										<div class="card dark-green summary-inline">
											<div class="card-body">
												<i class="icon fa fa-mobile fa-3x"></i>
												<div class="content">
													<div>CEDESSP660</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</li>                            
								<li class="gap"></li>
								<li class="gap"></li>
								<li class="gap"></li>
							</ul>
							<div class="cd-fail-message">Nada encontrado...</div>
						</section> <!-- cd-gallery -->
	
					</main>
				</div>				
	        </div>
</asp:Content>
