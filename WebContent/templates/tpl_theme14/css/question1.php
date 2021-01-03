<?php 
// tableau d'arguments
$args = array(
'posts_per_page' => 3,
'tag' => '19eme-siecle', //tag:mot clé: 19eme-siecle
'category_name' => 'ingenieurs'; // cat: ingenieurs
'order' => 'ASC' // ordre d'affichage
);
// Boucle
while ( $args->have_posts() ) : $args->the_post();
the_title();
echo '<div>';
the_content();
echo '</div>';
endwhile;
query_posts($args);
wp_reset_query(); // Reset de la boucle important !


?>