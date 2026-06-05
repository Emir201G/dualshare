package com.app.dualshare.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FirebaseTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // 1. Si no hay Header o no empieza con Bearer, dejamos pasar al siguiente filtro
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 2. Extraer el token limpio
            String token = header.substring(7);

            // 3. VALIDACIÓN REAL: Verificar el token usando el SDK de Firebase Admin
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid(); // Este es el UID único de Firebase (ej: u1_0.firebase_code)
            String email = decodedToken.getEmail();

            if (uid != null) {
                // 4. Crear el rol por defecto para Spring Security
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

                // 5. Crear el objeto de autenticación con el UID o Email como "Principal"
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(uid, null, authorities);

                // 6. 💡 CRÍTICO: Guardar la autenticación en el contexto de Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            // Si el token es viejo, trucho o expiró, devolvemos 401 Unauthorized de una
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token de autenticacion invalido o expirado.\"}");
            return;
        }

        // Si todo salió bien y el contexto está poblado, continúa la petición hacia el Controlador
        filterChain.doFilter(request, response);
    }
}