<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Task;
use Illuminate\Http\Request;

class TaskController extends Controller
{
    public function index()
    {
        // On récupère tous les tâches
        $Tasks = Task::all();

        // On retourne les informations des tâches en JSON
        return response()->json($Tasks);
    }

    public function store(Request $request)
    {
        // La validation de données
        $this->validate($request, [
           'title' => 'required|max:100'
        ]);

        // On crée un nouvel tâche
        $Task = Task::create([
            'title' => $request->title,
            'priority' => $request->priority
        ]);

        // On retourne les informations du nouvel tâche en JSON
        return response()->json($Task, 201);
    }

    public function show(Task $Task)
    {
        // On retourne les informations de l'tâche en JSON
        return response()->json($Task);
    }

    public function update(Request $request, Task $Task)
    {
        // La validation de données
        $this->validate($request, [
           'title' => 'required|max:100'
        ]);

        // On modifie les informations de l'tâche
        $Task->update([
            "title" => $request->title,
            "priority" => $request->priority
        ]);

        // On retourne la réponse JSON
        return response()->json();
    }

    public function destroy(Task $Task)
    {
        // On supprime l'tâche
        $Task->delete();

        // On retourne la réponse JSON
        return response()->json();
    }
}