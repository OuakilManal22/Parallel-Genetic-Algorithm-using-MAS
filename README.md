# Parallel-Genetic-Algorithm-using-MAS

Un algorithme génétique parallèle est une approche qui utilise plusieurs instances d'algorithmes génétiques pour résoudre une seule tâche. Chaque instance de l'algorithme génétique fonctionne de manière indépendante, essayant de trouver une solution au problème en question. Une fois que toutes les instances ont terminé leur exécution, le meilleur individu de chaque algorithme est sélectionné. Parmi ces individus sélectionnés, le meilleur individu global est déterminé, ce qui constitue la solution finale au problème.

Cette approche est communément appelée "Island Model" car les populations de chaque instance de l'algorithme génétique sont isolées les unes des autres, à l'instar de différentes populations de créatures pouvant exister sur des îles séparées dans la nature. Étant donné que les algorithmes génétiques sont indépendants les uns des autres, ils peuvent s'exécuter en parallèle, en tirant parti des processeurs multicœurs ou des environnements de calcul distribué.


Résumé des étapes principales du code :

IslandAgent : Cet agent est responsable de la mise en œuvre d'un algorithme génétique parallèle. Les étapes importantes incluent :

- Initialisation de la population avec des individus aléatoires.
- Tri de la population en fonction de la valeur de fitness des individus.
- Boucle principale comprenant les opérations de croisement, de mutation et de tri de la population.
- Envoi du meilleur individu de la population finale à l'agent MasterAgent via un message ACL.


MasterAgent : Cet agent est responsable de la communication avec l'agent IslandAgent et de l'affichage des messages reçus. Les étapes principales comprennent :

- Enregistrement de l'agent en tant que fournisseur de services de type "ga" dans le service de pages jaunes (DFService).
- Boucle cyclique pour recevoir les messages des autres agents.
- Affichage du contenu des messages reçus.

Individual : Cette classe représente un individu dans l'algorithme génétique. Elle contient un chromosome (tableau de caractères) et une valeur de fitness associée. Les individus sont comparables en fonction de leur fitness.


Ces codes mettent en œuvre une version simplifiée d'un algorithme génétique parallèle avec communication entre agents. Les détails spécifiques de l'algorithme génétique, tels que les opérations de croisement et de mutation, sont également inclus.
