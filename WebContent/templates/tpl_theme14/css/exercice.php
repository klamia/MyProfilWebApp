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


$args2 = array(
'posts_per_page' => 3,
'category_name' => 'ingenieurs'; // cat: ingenieurs
'order' => 'ASC' // ordre d'affichage
);


while ( $args2->have_posts() ) : $args2->the_post();
the_title();
<h4 class="section">tout les ingenieurs </h4>//sidebar la liste par ordre alphabétique de tous les ingénieurs
<ul class="list">
<?php wp_reset_postdata();
query_posts($args2);
while (have_posts()) : the_post(); ?>
<li><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></li>
<?php endwhile; ?>
</ul>