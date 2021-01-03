<?php

/**
 * @copyright	CedricKEIFLIN
 * httpwww.joomlack.fr
 * Template made with the joomla component Template Creator CK - http://www.joomlack.fr
 * tpl_theme14
 * @license GNUGPL
 * @version 1.0.0
 * */

// No direct access to this file
defined('_JEXEC') or die('Restricted access');
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>" dir="<?php echo $this->direction; ?>" >
<head>
<script  type="text/css">
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40872059-1', 'auto');
  ga('send', 'pageview');

</script>
    <jdoc:include type="head" />
    <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/system/css/system.css" type="text/css" />
    <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/system/css/general.css" type="text/css" />
    <?php if ($this->params->get('usebootstrap','')) { ?>
        <?php JHtml::_('bootstrap.framework'); ?>
        <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/bootstrap.css" type="text/css" />
        <?php JHtmlBootstrap::loadCss($includeMaincss = false, $this->direction); ?>
    <?php } ?>
    <?php if ($this->direction == 'rtl') { ?>
		<link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/default_rtl.css" type="text/css" />
                <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/template_rtl.css" type="text/css" />
    <?php } else { ?>
		<link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/default.css" type="text/css" />
                <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/template.css" type="text/css" />
	<?php } ?>
    <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/fonts/fonts.css" type="text/css" />
    <?php if ($this->params->get('useresponsive','1')) { ?>
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/mobile.css" type="text/css" />
    <?php } ?>
    <?php if ($this->params->get('usecsspie','1')) { ?><!--[if lte IE 8]>
  	<style type="text/css">
  	.pagenav a,.readmore a,.button,#bannermodule > div.inner,#nav > div.inner,#nav > div.inner ul.menu ul,#slideshow > div.inner,#modulestop > div.inner,#left > div.inner,#center > div.inner,#right > div.inner,#modulesbottommod1 > div.inner,#modulesbottommod2 > div.inner,#modulesbottommod3 > div.inner,#modulesbottommod4 > div.inner,#modulesbottommod5 > div.inner { behavior: url(<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/pie.htc) }
  	</style>
  	<![endif]--><?php } ?>
<?php
$nbmodules7 = (bool)$this->countModules('position-8') + (bool)$this->countModules('position-9') + (bool)$this->countModules('position-10') + (bool)$this->countModules('position-11') + (bool)$this->countModules('position-12');
?>

<?php
$mainclass = "";
if (!$this->countModules('position-7')) { $mainclass .= " noleft";}
if (!$this->countModules('position-6')) { $mainclass .= " noright";}
$mainclass = trim($mainclass); ?>

<?php
$nbmodules24 = (bool)$this->countModules('position-13') + (bool)$this->countModules('position-14') + (bool)$this->countModules('position-15') + (bool)$this->countModules('position-16') + (bool)$this->countModules('position-17');
?>

</head>
<body>
<div id="wrapper">
	<div class="container-fluid inner">
	<div id="banner">
		<div class="inner clearfix">
			<div id="bannerlogo" class="logobloc">
				<div class="inner clearfix">
					<?php if ($this->params->get('logolink')) { ?>
					<a href="<?php echo htmlspecialchars($this->params->get('logolink')); ?>">
					<?php } ?>
						<img src="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/images/logo.png" width="216px" height="53px" alt="CERIST ,Centre de recherche sur l information scientifique et technique" />
					<?php if ($this->params->get('logolink')) { ?>
					</a>
					<?php } ?>
					<?php if ($this->params->get('logodescription')) { ?>
					<div class="bannerlogodesc">
						<div class="inner clearfix"><?php echo htmlspecialchars($this->params->get('logodescription'));?></div>
					</div>
					<?php } ?>
				</div>
			</div>
			<?php if ($this->countModules('position-0')) : ?>
			<div id="bannermodule" class="logobloc">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-0" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
		</div>
<?php if ($this->countModules('position-01')) : ?>
	<div id="bannerlogo1">
		<div class="inner clearfix">
			<jdoc:include type="modules" name="position-01" style="xhtml" />
		</div>
	</div>
	<?php endif; ?>

	</div>
	<?php if ($this->countModules('position-1')) : ?>
	<div id="nav">
		<div class="inner clearfix">
			<jdoc:include type="modules" name="position-1" />
		</div>
	</div>
	<?php endif; ?>

	

	<?php if ($nbmodules7) : ?>
	<div id="modulestop">
		<div class="inner clearfix <?php echo 'n'.$nbmodules7 ?>">
			<?php if ($this->countModules('position-8')) : ?>
			<div id="modulestopmod1" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-8" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-9')) : ?>
			<div id="modulestopmod2" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-9" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-10')) : ?>
			<div id="modulestopmod3" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-10" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-11')) : ?>
			<div id="modulestopmod4" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-11" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-12')) : ?>
			<div id="modulestopmod5" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-12" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<div class="clr"></div>
		</div>
	</div>
	<?php endif; ?>
<?php if ($this->countModules('position-5')) : ?>
	<div id="slideshow">
		<div class="inner clearfix">
			<jdoc:include type="modules" name="position-5" style="xhtml" />
		</div>
	</div>
	<?php endif; ?>
	<div id="maincontent">
		<div class="inner clearfix">
		
			<div id="main" class="column main <?php echo $mainclass ?> ">
				<div class="inner clearfix">
							<div id="center" class="column center ">
								<div class="inner">
											<jdoc:include type="message" />
											<jdoc:include type="component" />
								</div>
							</div>
							<?php if ($this->countModules('position-6')) : ?>
							<div id="right" class="column column2">
								<div class="inner clearfix">
									<jdoc:include type="modules" name="position-6" style="xhtml" />
								</div>
							</div>
							<?php endif; ?>

				</div>
			</div>
			<div class="clr"></div>
		</div>
	</div>
	<?php if ($nbmodules24) : ?>
	<div id="modulesbottom">
		<div class="inner clearfix <?php echo 'n'.$nbmodules24 ?>">
			<?php if ($this->countModules('position-13')) : ?>
			<div id="modulesbottommod1" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-13" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-14')) : ?>
			<div id="modulesbottommod2" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-14" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-15')) : ?>
			<div id="modulesbottommod3" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-15" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-16')) : ?>
			<div id="modulesbottommod4" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-16" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<?php if ($this->countModules('position-17')) : ?>
			<div id="modulesbottommod5" class="flexiblemodule ">
				<div class="inner clearfix">
					<jdoc:include type="modules" name="position-17" style="xhtml" />
				</div>
			</div>
			<?php endif; ?>
			<div class="clr"></div>
		</div>
	</div>
	<?php endif; ?>

	<?php if ($this->countModules('position-3')) : ?>
	<div id="footer">
		<div class="inner clearfix">
			<jdoc:include type="modules" name="position-3" style="xhtml" />
		</div>
	</div>
	<?php endif; ?>


    </div>
</div>
<jdoc:include type="modules" name="debug" />
</body>
</html>