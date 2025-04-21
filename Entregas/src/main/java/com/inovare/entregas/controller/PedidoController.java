package com.inovare.entregas.controller;

        import com.inovare.entregas.model.Pedido;
        import com.inovare.entregas.service.PedidoService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.Optional;

        @RestController
        @RequestMapping("/pedidos")
        public class PedidoController {

            @Autowired
            private PedidoService pedidoService;

            @GetMapping("/calcular-frete")
            public double calcularFrete(@RequestParam double peso, @RequestParam String tipoFrete) {
                return pedidoService.calcularFrete(peso, tipoFrete);
            }

            @PostMapping
            public Pedido salvarPedido(@RequestBody Pedido pedido) {
                return pedidoService.salvarPedido(pedido);
            }

            @GetMapping("/{id}")
            public Optional<Pedido> buscarPedidoPorId(@PathVariable Long id) {
                return pedidoService.buscarPedidoPorId(id);
            }
        }